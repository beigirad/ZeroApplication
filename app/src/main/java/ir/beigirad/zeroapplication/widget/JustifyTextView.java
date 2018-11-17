package ir.beigirad.zeroapplication.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by farhad-mbp on 8/25/17.
 */

@SuppressWarnings("DefaultFileTemplate")
public class JustifyTextView extends AppCompatTextView {
    private Paint paint = new Paint();

    private String[] blocks;
    private float spaceOffset = 0;
    private float horizontalOffset = 0;
    private float verticalOffset = 0;
    private float horizontalFontOffset = 0;
    private float dirtyRegionWidth = 0;
    private boolean wrapEnabled = false;
    int left, top, right, bottom = 0;
    private Align _align = Align.RIGHT;
    private float strecthOffset;
    private float wrappedEdgeSpace;
    private String block;
    private String wrappedLine;
    private String[] lineAsWords;
    private Object[] wrappedObj;

    private Bitmap cache = null;
    private boolean cacheEnabled = false;

    public JustifyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //set a minimum of left and right padding so that the texts are not too close to the side screen
        this.setPadding(10, 0, 10, 10);
    }

    public JustifyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setPadding(10, 0, 10, 10);
    }

    public JustifyTextView(Context context) {
        super(context);
        this.setPadding(10, 0, 10, 10);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        // TODO Auto-generated method stub

        super.setPadding(left + 10, top, right + 10, bottom + 10);
    }

    @Override
    public void setDrawingCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }


    public void setText(int stResId, boolean wrap) {
        wrapEnabled = wrap;
        super.setText(stResId);
    }

    public void setText(CharSequence stResId, boolean wrap) {
        wrapEnabled = wrap;
        super.setText(stResId);
    }

    public void setTextAlign(Align align) {
        _align = align;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        // If wrap is disabled then,
        // request original onDraw
        if (!wrapEnabled) {
            super.onDraw(canvas);
            return;
        }

        // Active canas needs to be set
        // based on cacheEnabled
        Canvas activeCanvas = null;

        // Set the active canvas based on
        // whether cache is enabled
        if (cacheEnabled) {

            if (cache != null) {
                // Draw to the OS provided canvas
                // if the cache is not empty
                canvas.drawBitmap(cache, 0, 0, paint);
                return;
            } else {
                // Create a bitmap and set the activeCanvas
                // to the one derived from the bitmap
                cache = Bitmap.createBitmap(getWidth(), getHeight(),
                        Config.ARGB_4444);
                activeCanvas = new Canvas(cache);
            }
        } else {
            // Active canvas is the OS
            // provided canvas
            activeCanvas = canvas;
        }

        // Pull widget properties
        paint.setColor(getCurrentTextColor());
        paint.setTypeface(getTypeface());
        paint.setTextSize(getTextSize());
        paint.setTextAlign(_align);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        //minus out the paddings pixel
        dirtyRegionWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int maxLines = Integer.MAX_VALUE;
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            maxLines = getMaxLines();
        }
        int lines = 1;
        blocks = getText().toString().split("((?<=\n)|(?=\n))");
        verticalOffset = horizontalFontOffset = getLineHeight() - 0.5f; // Temp fix
        spaceOffset = paint.measureText(" ");

        for (int i = 0; i < blocks.length && lines <= maxLines; i++) {
            block = blocks[i];
            horizontalOffset = 0;

            if (block.length() == 0) {
                continue;
            } else if (block.equals("\n")) {
                verticalOffset += horizontalFontOffset;
                continue;
            }

            block = block.trim();

            if (block.length() == 0) {
                continue;
            }
            wrappedObj = TextJustifyUtils.createWrappedLine(block, paint, spaceOffset, dirtyRegionWidth);

            wrappedLine = ((String) wrappedObj[0]);
            wrappedEdgeSpace = (Float) wrappedObj[1];
            lineAsWords = wrappedLine.split(" ");
            strecthOffset = wrappedEdgeSpace != Float.MIN_VALUE ? wrappedEdgeSpace / (lineAsWords.length - 1) : 0;
            for (int j = 0; j < lineAsWords.length; j++) {
                String word = lineAsWords[j];
                if (lines == maxLines && j == lineAsWords.length - 1) {
                    activeCanvas.drawText("...", horizontalOffset, verticalOffset, paint);


                } else if (j == 0) {
                    //if it is the first word of the line, text will be drawn starting from right edge of textview
                    if (_align == Align.RIGHT) {
                        activeCanvas.drawText(word, getWidth() - (getPaddingRight()), verticalOffset, paint);
                        // add in the paddings to the horizontalOffset
                        horizontalOffset += getWidth() - (getPaddingRight());
                    } else {
                        activeCanvas.drawText(word, getPaddingLeft(), verticalOffset, paint);
                        horizontalOffset += getPaddingLeft();
                    }

                } else {
                    activeCanvas.drawText(word, horizontalOffset, verticalOffset, paint);
                }
                if (_align == Align.RIGHT)
                    horizontalOffset -= paint.measureText(word) + spaceOffset + strecthOffset;
                else
                    horizontalOffset += paint.measureText(word) + spaceOffset + strecthOffset;
            }

            lines++;

            if (blocks[i].length() > 0) {
                blocks[i] = blocks[i].substring(wrappedLine.length());
                verticalOffset += blocks[i].length() > 0 ? horizontalFontOffset : 0;
                i--;
            }
        }
        if (cacheEnabled) {
            // Draw the cache onto the OS provided
            // canvas.
            canvas.drawBitmap(cache, 0, 0, paint);
        }
    }

    private static class TextJustifyUtils {

        public static void justify(TextView textView) {
            Paint paint = new Paint();

            String[] blocks;
            float spaceOffset = 0;
            float textWrapWidth = 0;

            int spacesToSpread;
            float wrappedEdgeSpace;
            String block;
            String[] lineAsWords;
            String wrappedLine;
            String smb = "";
            Object[] wrappedObj;

            // Pull widget properties
            paint.setColor(textView.getCurrentTextColor());
            paint.setTypeface(textView.getTypeface());
            paint.setTextSize(textView.getTextSize());

            textWrapWidth = textView.getWidth();
            spaceOffset = paint.measureText(" ");
            blocks = textView.getText().toString().split("((?<=\n)|(?=\n))");

            if (textWrapWidth < 20) {
                return;
            }

            for (int i = 0; i < blocks.length; i++) {
                block = blocks[i];

                if (block.length() == 0) {
                    continue;
                } else if (block.equals("\n")) {
                    smb += block;
                    continue;
                }

                block = block.trim();

                if (block.length() == 0) continue;

                wrappedObj = TextJustifyUtils.createWrappedLine(block, paint, spaceOffset, textWrapWidth);
                wrappedLine = ((String) wrappedObj[0]);
                wrappedEdgeSpace = (Float) wrappedObj[1];
                lineAsWords = wrappedLine.split(" ");
                spacesToSpread = (int) (wrappedEdgeSpace != Float.MIN_VALUE ? wrappedEdgeSpace / spaceOffset : 0);

                for (String word : lineAsWords) {
                    smb += word + " ";

                    if (--spacesToSpread > 0) {
                        smb += " ";
                    }
                }

                smb = smb.trim();


                if (blocks[i].length() > 0) {
                    blocks[i] = blocks[i].substring(wrappedLine.length());

                    if (blocks[i].length() > 0) {
                        smb += "\n";
                    }

                    i--;
                }
            }

            textView.setGravity(Gravity.LEFT);
            textView.setText(smb);
        }

        public static Object[] createWrappedLine(String block, Paint paint, float spaceOffset, float maxWidth) {
            float cacheWidth = maxWidth;
            float origMaxWidth = maxWidth;

            String line = "";

            for (String word : block.split("\\s")) {
                cacheWidth = paint.measureText(word);
                maxWidth -= cacheWidth;

                if (maxWidth <= 0) {
                    return new Object[]{line, maxWidth + cacheWidth + spaceOffset};
                }

                line += word + " ";
                maxWidth -= spaceOffset;

            }

            if (paint.measureText(block) <= origMaxWidth) {
                return new Object[]{block, Float.MIN_VALUE};
            }
            return new Object[]{line, maxWidth};
        }

        final static String SYSTEM_NEWLINE = "\n";
        final static float COMPLEXITY = 5.12f;  //Reducing this will increase efficiency but will decrease effectiveness
        final static Paint p = new Paint();

    /* @author Mathew Kurian */

        public static void run(final TextView tv, float origWidth) {
            String s = tv.getText().toString();
            p.setTypeface(tv.getTypeface());
            String[] splits = s.split(SYSTEM_NEWLINE);
            float width = origWidth - 5;
            for (int x = 0; x < splits.length; x++)
                if (p.measureText(splits[x]) > width) {
                    splits[x] = wrap(splits[x], width, p);
                    String[] microSplits = splits[x].split(SYSTEM_NEWLINE);
                    for (int y = 0; y < microSplits.length - 1; y++)
                        microSplits[y] = justify(removeLast(microSplits[y], " "), width, p);
                    StringBuilder smb_internal = new StringBuilder();
                    for (int z = 0; z < microSplits.length; z++)
                        smb_internal.append(microSplits[z] + ((z + 1 < microSplits.length) ? SYSTEM_NEWLINE : ""));
                    splits[x] = smb_internal.toString();
                }
            final StringBuilder smb = new StringBuilder();
            for (String cleaned : splits)
                smb.append(cleaned + SYSTEM_NEWLINE);
            tv.setGravity(Gravity.LEFT);
            tv.setText(smb);
        }

        private static String wrap(String s, float width, Paint p) {
            String[] str = s.split("\\s"); //regex
            StringBuilder smb = new StringBuilder(); //save memory
            smb.append(SYSTEM_NEWLINE);
            for (int x = 0; x < str.length; x++) {
                float length = p.measureText(str[x]);
                String[] pieces = smb.toString().split(SYSTEM_NEWLINE);
                try {
                    if (p.measureText(pieces[pieces.length - 1]) + length > width)
                        smb.append(SYSTEM_NEWLINE);
                } catch (Exception e) {
                }
                smb.append(str[x] + " ");
            }
            return smb.toString().replaceFirst(SYSTEM_NEWLINE, "");
        }

        private static String removeLast(String s, String g) {
            if (s.contains(g)) {
                int index = s.lastIndexOf(g);
                int indexEnd = index + g.length();
                if (index == 0) return s.substring(1);
                else if (index == s.length() - 1) return s.substring(0, index);
                else
                    return s.substring(0, index) + s.substring(indexEnd);
            }
            return s;
        }

        private static String justifyOperation(String s, float width, Paint p) {
            float holder = (float) (COMPLEXITY * Math.random());
            while (s.contains(Float.toString(holder)))
                holder = (float) (COMPLEXITY * Math.random());
            String holder_string = Float.toString(holder);
            float lessThan = width;
            int timeOut = 100;
            int current = 0;
            while (p.measureText(s) < lessThan && current < timeOut) {
                s = s.replaceFirst(" ([^" + holder_string + "])", " " + holder_string + "$1");
                lessThan = p.measureText(holder_string) + lessThan - p.measureText(" ");
                current++;
            }
            String cleaned = s.replaceAll(holder_string, " ");
            return cleaned;
        }

        private static String justify(String s, float width, Paint p) {
            while (p.measureText(s) < width) {
                s = justifyOperation(s, width, p);
            }
            return s;
        }


    }
}
