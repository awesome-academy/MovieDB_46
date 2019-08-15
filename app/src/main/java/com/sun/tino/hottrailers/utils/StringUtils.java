package com.sun.tino.hottrailers.utils;

public class StringUtils {

    public static String getImageUrl(String image_path) {
        return new StringBuilder().append(Constants.BASE_IMAGE_PATH)
                .append(Constants.IMAGE_SIZE_W500)
                .append(image_path).toString();
    }
}
