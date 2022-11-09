package com.zalander.app;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;

public class ImageConverter {
    public String convert(BufferedImage image) {
        String res = "";
        ArrayList<ArrayList<ArrayList<ArrayList<Point>>>> blocks = getBlocks(image.getWidth(), image.getHeight());
        int height = blocks.size();
        int width = blocks.get(0).size();
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char character = blockToAscii(blocks.get(y).get(x), image);
                res += character;
            }
            res += "\n";
        }

        return res;
    }

    private char blockToAscii(ArrayList<ArrayList<Point>> block, BufferedImage image) {
        RGB rgb = getRGBAverageBlock(block, image);
        char[] charArr = {
            '█',
            '▓',
            '░',
            ':',
            '.',
            ' '
        };

        RGB[] rgbArr = {
            new RGB(255, 255, 255),
            new RGB(204, 204, 204),
            new RGB(143, 143, 143),
            new RGB(72, 72, 72),
            new RGB(21, 21, 21),
            new RGB(0, 0, 0)
        };
        int closest = getClosestColor(rgbArr, rgb);

        return charArr[closest];
    }

    private int getClosestColor(RGB[] rgbArr, RGB rgb) {
        int distance = 999;
        int res = 0;
        for (int i = 0; i < rgbArr.length; i++) {
            int dist = rgbDistance(rgbArr[i], rgb);
            if (dist < distance) { distance = dist; res = i;}
        }

        return res;
    }

    private int rgbDistance(RGB rgb1, RGB rgb2) {
        // woo euclidean distance !!!!!!!!!!!
        int distance = (int)Math.sqrt(
            Math.pow(rgb1.r - rgb2.r, 2) +
            Math.pow(rgb1.g - rgb2.g, 2) +
            Math.pow(rgb1.b - rgb2.b, 2)
        );
        return distance;
    }
    private RGB getRGBAverageBlock(ArrayList<ArrayList<Point>> block, BufferedImage image) {
        int yLength = block.size();
        int xLength = block.get(0).size();

        ArrayList<RGB> arr = new ArrayList<RGB>();

        for (int y = 0; y < yLength; y++) {
            for (int x = 0; x < xLength; x++) {
                Point point = block.get(y).get(x);
                int c = image.getRGB(point.x, point.y);
                Color color = new Color(c);

                RGB rgb = new RGB(color.getRed(), color.getGreen(), color.getBlue());
                arr.add(rgb);
            }
        }
        return rgbAverage(arr);
    }

    private RGB rgbAverage(ArrayList<RGB> arr) {
        int length = arr.size();

        int r = 0;
        int g = 0;
        int b = 0;

        for (int i = 0; i < length; i++) {
            RGB rgb = arr.get(i);
            r += rgb.r;
            g += rgb.g;
            b += rgb.b;
        }
        r = r / length;
        g = g / length;
        b = b / length;

        return new RGB(r, g, b);
    }


    // function to get all coordinates
    private ArrayList<ArrayList<Point>> math(int width, int height) {
        int videoWidth = Constants.videoWidth;
        int videoHeight = Constants.videoHeight;

        // woo i love java!!!!!
        ArrayList<ArrayList<Point>> res = new ArrayList<ArrayList<Point>>();
        for (int y = 0; y < videoHeight; y++) {
            ArrayList<Point> arr = new ArrayList<Point>();
            int ypos = (int)((double)((double)y / videoHeight) * height);
            for (int x = 0; x < videoWidth; x++) {
                int xpos = (int)((double)((double)x / videoWidth) * width);
                arr.add(new Point(xpos, ypos));
            }
            res.add(arr);
        }
        return res;
    }

    private ArrayList<ArrayList<ArrayList<ArrayList<Point>>>> getBlocks(int width, int height) {
        ArrayList<ArrayList<Point>> coordinates = math(width, height);
        int xLength = coordinates.get(0).size();
        int yLength = coordinates.size();
        // certified java classic
        ArrayList<ArrayList<ArrayList<ArrayList<Point>>>> res = new ArrayList<ArrayList<ArrayList<ArrayList<Point>>>>();

        for (int y = 0; y < yLength; y++) {
            int blockHeight;
            if (y + 3 == yLength) {blockHeight = coordinates.get(y + 1).get(0).y - coordinates.get(y).get(0).y;}
            else {blockHeight = coordinates.get(1).get(0).y - coordinates.get(0).get(0).y;}
            ArrayList<ArrayList<ArrayList<Point>>> arr = new ArrayList<ArrayList<ArrayList<Point>>>();

            for (int x = 0; x < xLength; x++) {
                int blockWidth;
                // if (x >= 197) {
                //     // sajkfjka
                // }
                if (x + 3 == xLength) {
                    blockWidth = coordinates.get(0).get(x + 1).x - coordinates.get(0).get(x).x;
                }
                else {blockWidth = coordinates.get(0).get(1).x - coordinates.get(0).get(0).x;}
                ArrayList<ArrayList<Point>> block = getBlock(blockWidth, blockHeight, coordinates.get(y).get(x));
                arr.add(block);
            }
            res.add(arr);
        }

        return res;
    }

    private ArrayList<ArrayList<Point>> getBlock(int width, int height, Point startingPoint) {
        ArrayList<ArrayList<Point>> res = new ArrayList<ArrayList<Point>>();
        for (int y = startingPoint.y; y < height + startingPoint.y; y++) {
            ArrayList<Point> arr = new ArrayList<Point>();
            for (int x = startingPoint.x; x < width + startingPoint.x; x++) {
                arr.add(new Point(x, y));
            }
            res.add(arr);
        }
        return res;
    }
}
