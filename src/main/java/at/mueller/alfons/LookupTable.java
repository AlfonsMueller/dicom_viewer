package at.mueller.alfons;

public class LookupTable {

    private int width= 20;

    private int center=20;

    private int alpha = 255;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width != this.width)
            if (width > 0)
                this.width = width;
            else {
                throw new IllegalArgumentException();
            }
    }

    public int getCenter() {
        return center;
    }

    public void setCenter(int center) {
        if (center != this.center) {
            this.center = center;
        }

    }

    public int aRGB(int value) {
        int y = (value - center + (width / 2)) * 255 / width;
        if (y > 255) {
            y = 255;
        }
        if (y < 0) {
            y = 0;
        }
        int rgb = getAlpha() << 24 | y << 16 | y << 8 | y;

        return rgb;



    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) throws Exception {
        if (alpha!= this.alpha) {
            if (alpha > 0 && this.alpha <= 255)
                this.alpha = alpha;

            else {
                throw new IllegalArgumentException();
            }


        }
    }

    public static void main(String[] args) {
        LookupTable l = new LookupTable();
        String s = String.format("%x",l.aRGB(6));
        System.out.println(s);
    }
}