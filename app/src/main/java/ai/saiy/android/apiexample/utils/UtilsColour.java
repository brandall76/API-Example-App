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
        colorList.add(new ColorName("Alice Blue", "Blue", Color.rgb(0xF0, 0xF8, 0xFF)));
        colorList.add(new ColorName("Antique White", "Beige", Color.rgb(0xFA, 0xEB, 0xD7)));
        colorList.add(new ColorName("Aqua", "Blue", Color.rgb(0x00, 0xFF, 0xFF)));
        colorList.add(new ColorName("Aquamarine", "Green", Color.rgb(0x7F, 0xFF, 0xD4)));
        colorList.add(new ColorName("Azure", "Blue", Color.rgb(0xF0, 0xFF, 0xFF)));
        colorList.add(new ColorName("Beige", "Beige", Color.rgb(0xF5, 0xF5, 0xDC)));
        colorList.add(new ColorName("Bisque","Beige", Color.rgb(0xFF, 0xE4, 0xC4)));
        colorList.add(new ColorName("Black", "Black", Color.rgb(0x00, 0x00, 0x00)));
        colorList.add(new ColorName("Blanched Almond", "Yellow", Color.rgb(0xFF, 0xEB, 0xCD)));
        colorList.add(new ColorName("Blue", "Blue", Color.rgb(0x00, 0x00, 0xFF)));
        colorList.add(new ColorName("Blue Violet", "Purple", Color.rgb(0x8A, 0x2B, 0xE2)));
        colorList.add(new ColorName("Brown", "Brown", Color.rgb(0xA5, 0x2A, 0x2A)));
        colorList.add(new ColorName("Burly Wood", "Brown", Color.rgb(0xDE, 0xB8, 0x87)));
        colorList.add(new ColorName("Cadet Blue", "Blue", Color.rgb(0x5F, 0x9E, 0xA0)));
        colorList.add(new ColorName("Chart reuse", "Light Green", Color.rgb(0x7F, 0xFF, 0x00)));
        colorList.add(new ColorName("Chocolate", "Brown", Color.rgb(0xD2, 0x69, 0x1E)));
        colorList.add(new ColorName("Coral", "Pink", Color.rgb(0xFF, 0x7F, 0x50)));
        colorList.add(new ColorName("Cornflower Blue", "Sky Blue", Color.rgb(0x64, 0x95, 0xED)));
        colorList.add(new ColorName("Cornsilk", "`Light Yellow`", Color.rgb(0xFF, 0xF8, 0xDC)));
        colorList.add(new ColorName("Crimson", "Red", Color.rgb(0xDC, 0x14, 0x3C)));
        colorList.add(new ColorName("Cyan", "Sky Blue", Color.rgb(0x00, 0xFF, 0xFF)));
        colorList.add(new ColorName("Dark Blue", "Blue", Color.rgb(0x00, 0x00, 0x8B)));
        colorList.add(new ColorName("Dark Cyan", "Green", Color.rgb(0x00, 0x8B, 0x8B)));
        colorList.add(new ColorName("Dark GoldenRod","Yellow", Color.rgb(0xB8, 0x86, 0x0B)));
        colorList.add(new ColorName("Dark Gray", "Gray", Color.rgb(0xA9, 0xA9, 0xA9)));
        colorList.add(new ColorName("Dark Green", "Green", Color.rgb(0x00, 0x64, 0x00)));
        colorList.add(new ColorName("Dark Khaki", "Khaki", Color.rgb(0xBD, 0xB7, 0x6B)));
        colorList.add(new ColorName("Dark Magenta", "Purple", Color.rgb(0x8B, 0x00, 0x8B)));
        colorList.add(new ColorName("Dark Olive Green", "Green",  Color.rgb(0x55, 0x6B, 0x2F)));
        colorList.add(new ColorName("Dark Orange", "Orange", Color.rgb(0xFF, 0x8C, 0x00)));
        colorList.add(new ColorName("Dark Orchid", "Purple", Color.rgb(0x99, 0x32, 0xCC)));
        colorList.add(new ColorName("Dark Red", "Red", Color.rgb(0x8B, 0x00, 0x00)));
        colorList.add(new ColorName("Dark Salmon", "Orange", Color.rgb(0xE9, 0x96, 0x7A)));
        colorList.add(new ColorName("Dark Sea Green", "Green", Color.rgb(0x8F, 0xBC, 0x8F)));
        colorList.add(new ColorName("Dark SlateBlue", "Blue", Color.rgb(0x48, 0x3D, 0x8B)));
        colorList.add(new ColorName("Dark Slate Gray", "Green", Color.rgb(0x2F, 0x4F, 0x4F)));
        colorList.add(new ColorName("Dark Turquoise", "Sky Blue", Color.rgb(0x00, 0xCE, 0xD1)));
        colorList.add(new ColorName("Dark Violet", "Purple", Color.rgb(0x94, 0x00, 0xD3)));
        colorList.add(new ColorName("Deep Pink", "Pink", Color.rgb(0xFF, 0x14, 0x93)));
        colorList.add(new ColorName("Deep Sky Blue", "Blue", Color.rgb(0x00, 0xBF, 0xFF)));
        colorList.add(new ColorName("Dim Gray", "Gray", Color.rgb(0x69, 0x69, 0x69)));
        colorList.add(new ColorName("Dodger Blue", "Blue", Color.rgb(0x1E, 0x90, 0xFF)));
        colorList.add(new ColorName("Fire Brick",  "Red", Color.rgb(0xB2, 0x22, 0x22)));
        colorList.add(new ColorName("Floral White", "White", Color.rgb(0xFF, 0xFA, 0xF0)));
        colorList.add(new ColorName("Forest Green", "Green", Color.rgb(0x22, 0x8B, 0x22)));
        colorList.add(new ColorName("Fuchsia", "Purple", Color.rgb(0xFF, 0x00, 0xFF)));
        colorList.add(new ColorName("Gainsboro", "Gray", Color.rgb(0xDC, 0xDC, 0xDC)));
        colorList.add(new ColorName("Ghost White", "White", Color.rgb(0xF8, 0xF8, 0xFF)));
        colorList.add(new ColorName("Gold", "Gold", Color.rgb(0xFF, 0xD7, 0x00)));
        colorList.add(new ColorName("Golden Rod", "Yellow", Color.rgb(0xDA, 0xA5, 0x20)));
        colorList.add(new ColorName("Gray", "Gray", Color.rgb(0x80, 0x80, 0x80)));
        colorList.add(new ColorName("Green", "Green", Color.rgb(0x00, 0x80, 0x00)));
        colorList.add(new ColorName("Green Yellow", "Light Green", Color.rgb(0xAD, 0xFF, 0x2F)));
        colorList.add(new ColorName("Honey Dew", "Light Green", Color.rgb(0xF0, 0xFF, 0xF0)));
        colorList.add(new ColorName("Hot Pink", "Pink", Color.rgb(0xFF, 0x69, 0xB4)));
        colorList.add(new ColorName("Indian Red","Red",  Color.rgb(0xCD, 0x5C, 0x5C)));
        colorList.add(new ColorName("Indigo", "Purple", Color.rgb(0x4B, 0x00, 0x82)));
        colorList.add(new ColorName("Ivory", "Ivory", Color.rgb(0xFF, 0xFF, 0xF0)));
        colorList.add(new ColorName("Khaki", "Khaki", Color.rgb(0xF0, 0xE6, 0x8C)));
        colorList.add(new ColorName("Lavender", "Purple", Color.rgb(0xE6, 0xE6, 0xFA)));
        colorList.add(new ColorName("Lavender Blush", "Pink", Color.rgb(0xFF, 0xF0, 0xF5)));
        colorList.add(new ColorName("Lawn Green", "Light Green", Color.rgb(0x7C, 0xFC, 0x00)));
        colorList.add(new ColorName("Lemon Chiffon", "Yellow", Color.rgb(0xFF, 0xFA, 0xCD)));
        colorList.add(new ColorName("Light Blue", "Light Blue", Color.rgb(0xAD, 0xD8, 0xE6)));
        colorList.add(new ColorName("Light Coral", "Pink", Color.rgb(0xF0, 0x80, 0x80)));
        colorList.add(new ColorName("Light Cyan", "Sky Blue", Color.rgb(0xE0, 0xFF, 0xFF)));
        colorList.add(new ColorName("Light Golden Rod Yellow", Color.rgb(0xFA, 0xFA, 0xD2)));
        colorList.add(new ColorName("Light Gray", "Gray", Color.rgb(0xD3, 0xD3, 0xD3)));
        colorList.add(new ColorName("Light Green","Light Green", Color.rgb(0x90, 0xEE, 0x90)));
        colorList.add(new ColorName("Light Pink", "Pink", Color.rgb(0xFF, 0xB6, 0xC1)));
        colorList.add(new ColorName("Light Salmon", "Orange", Color.rgb(0xFF, 0xA0, 0x7A)));
        colorList.add(new ColorName("Light SeaGreen", "Sky Blue", Color.rgb(0x20, 0xB2, 0xAA)));
        colorList.add(new ColorName("Light Sky Blue",  "Sky Blue", Color.rgb(0x87, 0xCE, 0xFA)));
        colorList.add(new ColorName("Light Slate Gray", "Gray", Color.rgb(0x77, 0x88, 0x99)));
        colorList.add(new ColorName("Light Steel Blue", "Blue", Color.rgb(0xB0, 0xC4, 0xDE)));
        colorList.add(new ColorName("Light Yellow", "Light Yellow", Color.rgb(0xFF, 0xFF, 0xE0)));
        colorList.add(new ColorName("Lime", "Light Green", Color.rgb(0x00, 0xFF, 0x00)));
        colorList.add(new ColorName("Lime Green", "Light Green", Color.rgb(0x32, 0xCD, 0x32)));
        colorList.add(new ColorName("Linen", "Beige", Color.rgb(0xFA, 0xF0, 0xE6)));
        colorList.add(new ColorName("Magenta", "Purple", Color.rgb(0xFF, 0x00, 0xFF)));
        colorList.add(new ColorName("Maroon", "Red", Color.rgb(0x80, 0x00, 0x00)));
        colorList.add(new ColorName("Medium Aqua Marine", "Green", Color.rgb(0x66, 0xCD, 0xAA)));
        colorList.add(new ColorName("Medium Blue", "Blue", Color.rgb(0x00, 0x00, 0xCD)));
        colorList.add(new ColorName("Medium Orchid", "Purple", Color.rgb(0xBA, 0x55, 0xD3)));
        colorList.add(new ColorName("Medium Purple", "Purple", Color.rgb(0x93, 0x70, 0xDB)));
        colorList.add(new ColorName("Medium Sea Green", "Green", Color.rgb(0x3C, 0xB3, 0x71)));
        colorList.add(new ColorName("Medium Slate Blue", "Blue", Color.rgb(0x7B, 0x68, 0xEE)));
        colorList.add(new ColorName("Medium Spring Green", "Green", Color.rgb(0x00, 0xFA, 0x9A)));
        colorList.add(new ColorName("Medium Turquoise", "Blue", Color.rgb(0x48, 0xD1, 0xCC)));
        colorList.add(new ColorName("Medium Violet Red", "Purple", Color.rgb(0xC7, 0x15, 0x85)));
        colorList.add(new ColorName("Midnight Blue", "Navy", Color.rgb(0x19, 0x19, 0x70)));
        colorList.add(new ColorName("Mint Cream", "Light Green", Color.rgb(0xF5, 0xFF, 0xFA)));
        colorList.add(new ColorName("Misty Rose", "Pink", Color.rgb(0xFF, 0xE4, 0xE1)));
        colorList.add(new ColorName("Moccasin", "Yellow", Color.rgb(0xFF, 0xE4, 0xB5)));
        colorList.add(new ColorName("Navajo White", "Beige", Color.rgb(0xFF, 0xDE, 0xAD)));
        colorList.add(new ColorName("Navy", "Navy", Color.rgb(0x00, 0x00, 0x80)));
        colorList.add(new ColorName("Old Lace", "Beige", Color.rgb(0xFD, 0xF5, 0xE6)));
        colorList.add(new ColorName("Olive", "Green", Color.rgb(0x80, 0x80, 0x00)));
        colorList.add(new ColorName("Olive Drab", "Green", Color.rgb(0x6B, 0x8E, 0x23)));
        colorList.add(new ColorName("Orange", "Orange", Color.rgb(0xFF, 0xA5, 0x00)));
        colorList.add(new ColorName("Orange Red", "Orange", Color.rgb(0xFF, 0x45, 0x00)));
        colorList.add(new ColorName("Orchid", "Purple", Color.rgb(0xDA, 0x70, 0xD6)));
        colorList.add(new ColorName("Pale Golden Rod", "Yellow", Color.rgb(0xEE, 0xE8, 0xAA)));
        colorList.add(new ColorName("Pale Green", "Light Green", Color.rgb(0x98, 0xFB, 0x98)));
        colorList.add(new ColorName("Pale Turquoise", "Sky Blue", Color.rgb(0xAF, 0xEE, 0xEE)));
        colorList.add(new ColorName("Pale Violet Red", "Pink", Color.rgb(0xDB, 0x70, 0x93)));
        colorList.add(new ColorName("Papaya Whip", "Beige", Color.rgb(0xFF, 0xEF, 0xD5)));
        colorList.add(new ColorName("Peach Puff", "Beige", Color.rgb(0xFF, 0xDA, 0xB9)));
        colorList.add(new ColorName("Peru", "Brown", Color.rgb(0xCD, 0x85, 0x3F)));
        colorList.add(new ColorName("Pink", "Pink", Color.rgb(0xFF, 0xC0, 0xCB)));
        colorList.add(new ColorName("Plum", "Purple", Color.rgb(0xDD, 0xA0, 0xDD)));
        colorList.add(new ColorName("Powder Blue", "Blue", Color.rgb(0xB0, 0xE0, 0xE6)));
        colorList.add(new ColorName("Purple", "Purple", Color.rgb(0x80, 0x00, 0x80)));
        colorList.add(new ColorName("Red", "Red", Color.rgb(0xFF, 0x00, 0x00)));
        colorList.add(new ColorName("Rosy Brown", "Brown", Color.rgb(0xBC, 0x8F, 0x8F)));
        colorList.add(new ColorName("Royal Blue", "Blue", Color.rgb(0x41, 0x69, 0xE1)));
        colorList.add(new ColorName("Saddle Brown", "Brown", Color.rgb(0x8B, 0x45, 0x13)));
        colorList.add(new ColorName("Salmon", "Pink", Color.rgb(0xFA, 0x80, 0x72)));
        colorList.add(new ColorName("Sandy Brown", "Orange", Color.rgb(0xF4, 0xA4, 0x60)));
        colorList.add(new ColorName("Sea Green", "Green", Color.rgb(0x2E, 0x8B, 0x57)));
        colorList.add(new ColorName("Sea Shell", "White", Color.rgb(0xFF, 0xF5, 0xEE)));
        colorList.add(new ColorName("Sienna", "Brown", Color.rgb(0xA0, 0x52, 0x2D)));
        colorList.add(new ColorName("Silver", "Silver", Color.rgb(0xC0, 0xC0, 0xC0)));
        colorList.add(new ColorName("Sky Blue", "Sky Blue", Color.rgb(0x87, 0xCE, 0xEB)));
        colorList.add(new ColorName("Slate Blue", "Blue", Color.rgb(0x6A, 0x5A, 0xCD)));
        colorList.add(new ColorName("Slate Gray", "Gray", Color.rgb(0x70, 0x80, 0x90)));
        colorList.add(new ColorName("Snow", "White", Color.rgb(0xFF, 0xFA, 0xFA)));
        colorList.add(new ColorName("Spring Green", "Green", Color.rgb(0x00, 0xFF, 0x7F)));
        colorList.add(new ColorName("Steel Blue", "Blue", Color.rgb(0x46, 0x82, 0xB4)));
        colorList.add(new ColorName("Tan", "Blue", Color.rgb(0xD2, 0xB4, 0x8C)));
        colorList.add(new ColorName("Teal", "Green", Color.rgb(0x00, 0x80, 0x80)));
        colorList.add(new ColorName("Thistle", "Purple", Color.rgb(0xD8, 0xBF, 0xD8)));
        colorList.add(new ColorName("Tomato", "Red", Color.rgb(0xFF, 0x63, 0x47)));
        colorList.add(new ColorName("Turquoise", "Blue", Color.rgb(0x40, 0xE0, 0xD0)));
        colorList.add(new ColorName("Violet", "Purple", Color.rgb(0xEE, 0x82, 0xEE)));
        colorList.add(new ColorName("Wheat", "Brown", Color.rgb(0xF5, 0xDE, 0xB3)));
        colorList.add(new ColorName("White", "White", Color.rgb(0xFF, 0xFF, 0xFF)));
        colorList.add(new ColorName("White Smoke", "White", Color.rgb(0xF5, 0xF5, 0xF5)));
        colorList.add(new ColorName("Yellow", "Yellow", Color.rgb(0xFF, 0xFF, 0x00)));
        colorList.add(new ColorName("Yellow Green", "Light Green", Color.rgb(0x9A, 0xCD, 0x32)));
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
