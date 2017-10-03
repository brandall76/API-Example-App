/*
 *    Copyright 2016 Saiy™ Ltd
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package ai.saiy.android.apiexample.utils;

import android.graphics.Color;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by benrandall76@gmail.com on 03/10/2016.
 * <p>
 * Example code extracted from the link below. License unknown, assumed Apache. If you are the copyright
 * holder of this work, or your attribution is missing, please contact me, so I can correct the file
 * as necessary.
 * <p>
 * Java Code to get a color name from rgb/hex value/awt color
 * <p>
 * The part of looking up a color name from the rgb values is edited from
 * https://gist.github.com/nightlark/6482130#file-gistfile1-java (that has some errors) by Ryan Mast (nightlark)
 *
 * @author Xiaoxiao Li
 */
public class UtilsColour {


    /**
     * Initialize the color list that we have.
     */
    private ArrayList<ColorName> initColorList() {

        ArrayList<ColorName> colorList = new ArrayList<>();
        colorList.add(new ColorName("Alice Blue", Color.rgb(0xF0, 0xF8, 0xFF)));
        colorList.add(new ColorName("Antique White", Color.rgb(0xFA, 0xEB, 0xD7)));
        colorList.add(new ColorName("Aqua", Color.rgb(0x00, 0xFF, 0xFF)));
        colorList.add(new ColorName("Aquamarine", Color.rgb(0x7F, 0xFF, 0xD4)));
        colorList.add(new ColorName("Azure", Color.rgb(0xF0, 0xFF, 0xFF)));
        colorList.add(new ColorName("Beige", Color.rgb(0xF5, 0xF5, 0xDC)));
        colorList.add(new ColorName("Bisque", Color.rgb(0xFF, 0xE4, 0xC4)));
        colorList.add(new ColorName("Black", Color.rgb(0x00, 0x00, 0x00)));
        colorList.add(new ColorName("Blanched Almond", Color.rgb(0xFF, 0xEB, 0xCD)));
        colorList.add(new ColorName("Blue", Color.rgb(0x00, 0x00, 0xFF)));
        colorList.add(new ColorName("Blue Violet", Color.rgb(0x8A, 0x2B, 0xE2)));
        colorList.add(new ColorName("Brown", Color.rgb(0xA5, 0x2A, 0x2A)));
        colorList.add(new ColorName("Burly Wood", Color.rgb(0xDE, 0xB8, 0x87)));
        colorList.add(new ColorName("Cadet Blue", Color.rgb(0x5F, 0x9E, 0xA0)));
        colorList.add(new ColorName("Chart reuse", Color.rgb(0x7F, 0xFF, 0x00)));
        colorList.add(new ColorName("Chocolate", Color.rgb(0xD2, 0x69, 0x1E)));
        colorList.add(new ColorName("Coral", Color.rgb(0xFF, 0x7F, 0x50)));
        colorList.add(new ColorName("Cornflower Blue", Color.rgb(0x64, 0x95, 0xED)));
        colorList.add(new ColorName("Cornsilk", Color.rgb(0xFF, 0xF8, 0xDC)));
        colorList.add(new ColorName("Crimson", Color.rgb(0xDC, 0x14, 0x3C)));
        colorList.add(new ColorName("Cyan", Color.rgb(0x00, 0xFF, 0xFF)));
        colorList.add(new ColorName("Dark Blue", Color.rgb(0x00, 0x00, 0x8B)));
        colorList.add(new ColorName("Dark Cyan", Color.rgb(0x00, 0x8B, 0x8B)));
        colorList.add(new ColorName("Dark GoldenRod", Color.rgb(0xB8, 0x86, 0x0B)));
        colorList.add(new ColorName("Dark Gray", Color.rgb(0xA9, 0xA9, 0xA9)));
        colorList.add(new ColorName("Dark Green", Color.rgb(0x00, 0x64, 0x00)));
        colorList.add(new ColorName("Dark Khaki", Color.rgb(0xBD, 0xB7, 0x6B)));
        colorList.add(new ColorName("Dark Magenta", Color.rgb(0x8B, 0x00, 0x8B)));
        colorList.add(new ColorName("Dark Olive Green", Color.rgb(0x55, 0x6B, 0x2F)));
        colorList.add(new ColorName("Dark Orange", Color.rgb(0xFF, 0x8C, 0x00)));
        colorList.add(new ColorName("Dark Orchid", Color.rgb(0x99, 0x32, 0xCC)));
        colorList.add(new ColorName("Dark Red", Color.rgb(0x8B, 0x00, 0x00)));
        colorList.add(new ColorName("Dark Salmon", Color.rgb(0xE9, 0x96, 0x7A)));
        colorList.add(new ColorName("Dark Sea Green", Color.rgb(0x8F, 0xBC, 0x8F)));
        colorList.add(new ColorName("Dark SlateBlue", Color.rgb(0x48, 0x3D, 0x8B)));
        colorList.add(new ColorName("Dark Slate Gray", Color.rgb(0x2F, 0x4F, 0x4F)));
        colorList.add(new ColorName("Dark Turquoise", Color.rgb(0x00, 0xCE, 0xD1)));
        colorList.add(new ColorName("Dark Violet", Color.rgb(0x94, 0x00, 0xD3)));
        colorList.add(new ColorName("Deep Pink", Color.rgb(0xFF, 0x14, 0x93)));
        colorList.add(new ColorName("Deep Sky Blue", Color.rgb(0x00, 0xBF, 0xFF)));
        colorList.add(new ColorName("Dim Gray", Color.rgb(0x69, 0x69, 0x69)));
        colorList.add(new ColorName("Dodger Blue", Color.rgb(0x1E, 0x90, 0xFF)));
        colorList.add(new ColorName("Fire Brick", Color.rgb(0xB2, 0x22, 0x22)));
        colorList.add(new ColorName("Floral White", Color.rgb(0xFF, 0xFA, 0xF0)));
        colorList.add(new ColorName("Forest Green", Color.rgb(0x22, 0x8B, 0x22)));
        colorList.add(new ColorName("Fuchsia", Color.rgb(0xFF, 0x00, 0xFF)));
        colorList.add(new ColorName("Gainsboro", Color.rgb(0xDC, 0xDC, 0xDC)));
        colorList.add(new ColorName("Ghost White", Color.rgb(0xF8, 0xF8, 0xFF)));
        colorList.add(new ColorName("Gold", Color.rgb(0xFF, 0xD7, 0x00)));
        colorList.add(new ColorName("Golden Rod", Color.rgb(0xDA, 0xA5, 0x20)));
        colorList.add(new ColorName("Gray", Color.rgb(0x80, 0x80, 0x80)));
        colorList.add(new ColorName("Green", Color.rgb(0x00, 0x80, 0x00)));
        colorList.add(new ColorName("Green Yellow", Color.rgb(0xAD, 0xFF, 0x2F)));
        colorList.add(new ColorName("Honey Dew", Color.rgb(0xF0, 0xFF, 0xF0)));
        colorList.add(new ColorName("Hot Pink", Color.rgb(0xFF, 0x69, 0xB4)));
        colorList.add(new ColorName("Indian Red", Color.rgb(0xCD, 0x5C, 0x5C)));
        colorList.add(new ColorName("Indigo", Color.rgb(0x4B, 0x00, 0x82)));
        colorList.add(new ColorName("Ivory", Color.rgb(0xFF, 0xFF, 0xF0)));
        colorList.add(new ColorName("Khaki", Color.rgb(0xF0, 0xE6, 0x8C)));
        colorList.add(new ColorName("Lavender", Color.rgb(0xE6, 0xE6, 0xFA)));
        colorList.add(new ColorName("Lavender Blush", Color.rgb(0xFF, 0xF0, 0xF5)));
        colorList.add(new ColorName("Lawn Green", Color.rgb(0x7C, 0xFC, 0x00)));
        colorList.add(new ColorName("Lemon Chiffon", Color.rgb(0xFF, 0xFA, 0xCD)));
        colorList.add(new ColorName("Light Blue", Color.rgb(0xAD, 0xD8, 0xE6)));
        colorList.add(new ColorName("Light Coral", Color.rgb(0xF0, 0x80, 0x80)));
        colorList.add(new ColorName("Light Cyan", Color.rgb(0xE0, 0xFF, 0xFF)));
        colorList.add(new ColorName("Light Golden Rod Yellow", Color.rgb(0xFA, 0xFA, 0xD2)));
        colorList.add(new ColorName("Light Gray", Color.rgb(0xD3, 0xD3, 0xD3)));
        colorList.add(new ColorName("Light Green", Color.rgb(0x90, 0xEE, 0x90)));
        colorList.add(new ColorName("Light Pink", Color.rgb(0xFF, 0xB6, 0xC1)));
        colorList.add(new ColorName("Light Salmon", Color.rgb(0xFF, 0xA0, 0x7A)));
        colorList.add(new ColorName("Light SeaGreen", Color.rgb(0x20, 0xB2, 0xAA)));
        colorList.add(new ColorName("Light Sky Blue", Color.rgb(0x87, 0xCE, 0xFA)));
        colorList.add(new ColorName("Light Slate Gray", Color.rgb(0x77, 0x88, 0x99)));
        colorList.add(new ColorName("Light Steel Blue", Color.rgb(0xB0, 0xC4, 0xDE)));
        colorList.add(new ColorName("Light Yellow", Color.rgb(0xFF, 0xFF, 0xE0)));
        colorList.add(new ColorName("Lime", Color.rgb(0x00, 0xFF, 0x00)));
        colorList.add(new ColorName("Lime Green", Color.rgb(0x32, 0xCD, 0x32)));
        colorList.add(new ColorName("Linen", Color.rgb(0xFA, 0xF0, 0xE6)));
        colorList.add(new ColorName("Magenta", Color.rgb(0xFF, 0x00, 0xFF)));
        colorList.add(new ColorName("Maroon", Color.rgb(0x80, 0x00, 0x00)));
        colorList.add(new ColorName("Medium Aqua Marine", Color.rgb(0x66, 0xCD, 0xAA)));
        colorList.add(new ColorName("Medium Blue", Color.rgb(0x00, 0x00, 0xCD)));
        colorList.add(new ColorName("Medium Orchid", Color.rgb(0xBA, 0x55, 0xD3)));
        colorList.add(new ColorName("Medium Purple", Color.rgb(0x93, 0x70, 0xDB)));
        colorList.add(new ColorName("Medium Sea Green", Color.rgb(0x3C, 0xB3, 0x71)));
        colorList.add(new ColorName("Medium Slate Blue", Color.rgb(0x7B, 0x68, 0xEE)));
        colorList.add(new ColorName("Medium Spring Green", Color.rgb(0x00, 0xFA, 0x9A)));
        colorList.add(new ColorName("Medium Turquoise", Color.rgb(0x48, 0xD1, 0xCC)));
        colorList.add(new ColorName("Medium Violet Red", Color.rgb(0xC7, 0x15, 0x85)));
        colorList.add(new ColorName("Midnight Blue", Color.rgb(0x19, 0x19, 0x70)));
        colorList.add(new ColorName("Mint Cream", Color.rgb(0xF5, 0xFF, 0xFA)));
        colorList.add(new ColorName("Misty Rose", Color.rgb(0xFF, 0xE4, 0xE1)));
        colorList.add(new ColorName("Moccasin", Color.rgb(0xFF, 0xE4, 0xB5)));
        colorList.add(new ColorName("Navajo White", Color.rgb(0xFF, 0xDE, 0xAD)));
        colorList.add(new ColorName("Navy", Color.rgb(0x00, 0x00, 0x80)));
        colorList.add(new ColorName("Old Lace", Color.rgb(0xFD, 0xF5, 0xE6)));
        colorList.add(new ColorName("Olive", Color.rgb(0x80, 0x80, 0x00)));
        colorList.add(new ColorName("Olive Drab", Color.rgb(0x6B, 0x8E, 0x23)));
        colorList.add(new ColorName("Orange", Color.rgb(0xFF, 0xA5, 0x00)));
        colorList.add(new ColorName("Orange Red", Color.rgb(0xFF, 0x45, 0x00)));
        colorList.add(new ColorName("Orchid", Color.rgb(0xDA, 0x70, 0xD6)));
        colorList.add(new ColorName("Pale Golden Rod", Color.rgb(0xEE, 0xE8, 0xAA)));
        colorList.add(new ColorName("Pale Green", Color.rgb(0x98, 0xFB, 0x98)));
        colorList.add(new ColorName("Pale Turquoise", Color.rgb(0xAF, 0xEE, 0xEE)));
        colorList.add(new ColorName("Pale Violet Red", Color.rgb(0xDB, 0x70, 0x93)));
        colorList.add(new ColorName("Papaya Whip", Color.rgb(0xFF, 0xEF, 0xD5)));
        colorList.add(new ColorName("Peach Puff", Color.rgb(0xFF, 0xDA, 0xB9)));
        colorList.add(new ColorName("Peru", Color.rgb(0xCD, 0x85, 0x3F)));
        colorList.add(new ColorName("Pink", Color.rgb(0xFF, 0xC0, 0xCB)));
        colorList.add(new ColorName("Plum", Color.rgb(0xDD, 0xA0, 0xDD)));
        colorList.add(new ColorName("Powder Blue", Color.rgb(0xB0, 0xE0, 0xE6)));
        colorList.add(new ColorName("Purple", Color.rgb(0x80, 0x00, 0x80)));
        colorList.add(new ColorName("Red", Color.rgb(0xFF, 0x00, 0x00)));
        colorList.add(new ColorName("Rosy Brown", Color.rgb(0xBC, 0x8F, 0x8F)));
        colorList.add(new ColorName("Royal Blue", Color.rgb(0x41, 0x69, 0xE1)));
        colorList.add(new ColorName("Saddle Brown", Color.rgb(0x8B, 0x45, 0x13)));
        colorList.add(new ColorName("Salmon", Color.rgb(0xFA, 0x80, 0x72)));
        colorList.add(new ColorName("Sandy Brown", Color.rgb(0xF4, 0xA4, 0x60)));
        colorList.add(new ColorName("Sea Green", Color.rgb(0x2E, 0x8B, 0x57)));
        colorList.add(new ColorName("Sea Shell", Color.rgb(0xFF, 0xF5, 0xEE)));
        colorList.add(new ColorName("Sienna", Color.rgb(0xA0, 0x52, 0x2D)));
        colorList.add(new ColorName("Silver", Color.rgb(0xC0, 0xC0, 0xC0)));
        colorList.add(new ColorName("Sky Blue", Color.rgb(0x87, 0xCE, 0xEB)));
        colorList.add(new ColorName("Slate Blue", Color.rgb(0x6A, 0x5A, 0xCD)));
        colorList.add(new ColorName("Slate Gray", Color.rgb(0x70, 0x80, 0x90)));
        colorList.add(new ColorName("Snow", Color.rgb(0xFF, 0xFA, 0xFA)));
        colorList.add(new ColorName("Spring Green", Color.rgb(0x00, 0xFF, 0x7F)));
        colorList.add(new ColorName("Steel Blue", Color.rgb(0x46, 0x82, 0xB4)));
        colorList.add(new ColorName("Tan", Color.rgb(0xD2, 0xB4, 0x8C)));
        colorList.add(new ColorName("Teal", Color.rgb(0x00, 0x80, 0x80)));
        colorList.add(new ColorName("Thistle", Color.rgb(0xD8, 0xBF, 0xD8)));
        colorList.add(new ColorName("Tomato", Color.rgb(0xFF, 0x63, 0x47)));
        colorList.add(new ColorName("Turquoise", Color.rgb(0x40, 0xE0, 0xD0)));
        colorList.add(new ColorName("Violet", Color.rgb(0xEE, 0x82, 0xEE)));
        colorList.add(new ColorName("Wheat", Color.rgb(0xF5, 0xDE, 0xB3)));
        colorList.add(new ColorName("White", Color.rgb(0xFF, 0xFF, 0xFF)));
        colorList.add(new ColorName("White Smoke", Color.rgb(0xF5, 0xF5, 0xF5)));
        colorList.add(new ColorName("Yellow", Color.rgb(0xFF, 0xFF, 0x00)));
        colorList.add(new ColorName("Yellow Green", Color.rgb(0x9A, 0xCD, 0x32)));
        return colorList;
    }

    /**
     * Iterate through the voice data to attempt to match a colour name
     *
     * @param voiceData the voice data
     * @return the integer representation of the colour or black if the colour
     * is not detected.
     */
    public static int getColourFromName(@NonNull final ArrayList<String> voiceData) {

        final ArrayList<ColorName> colorNames = new UtilsColour().initColorList();

        for (final String vd : voiceData) {
            for (final ColorName colorName : colorNames) {

                if (vd.toLowerCase(Locale.US).matches(colorName.getName().toLowerCase(Locale.US))) {
                    return colorName.getColour();
                }
            }
        }

        return Color.rgb(0x00, 0x00, 0x00);
    }

    /**
     * SubClass of ColorUtils. In order to lookup color name
     *
     * @author Xiaoxiao Li
     */
    private class ColorName {

        private int colour;
        private String name;

        private ColorName(final String name, int color) {
            this.name = name;
            this.colour = color;
        }

        private int getColour() {
            return colour;
        }

        private String getName() {
            return name;
        }
    }
}
