package com.sun.tino.hottrailers.utils;

public class StringUtils {

    public static String getImageUrl(String image_path) {
        return new StringBuilder().append(Constants.BASE_IMAGE_PATH)
                .append(Constants.IMAGE_SIZE_W500)
                .append(image_path).toString();
    }

    public static String getThumbnail(String trailerKey) {
        return String.format(Constants.BASE_THUMBNAIL_PATH, trailerKey);
    }

    public static String getSmallImage(String image_path) {
        return new StringBuilder().append(Constants.BASE_IMAGE_PATH)
                .append(Constants.IMAGE_SIZE_W200)
                .append(image_path).toString();
    }
}
