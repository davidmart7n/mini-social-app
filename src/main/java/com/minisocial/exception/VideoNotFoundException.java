package com.minisocial.exception;

public class VideoNotFoundException extends RuntimeException {
    
    public VideoNotFoundException (Long videoId){
        super("The video with Id: " + videoId + " was not found.");
    }
}