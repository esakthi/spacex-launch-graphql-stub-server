package com.citi.tts.spacex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinksInput {
    private String mission_patch;
    private String mission_patch_small;
    private String reddit_campaign;
    private String reddit_launch;
    private String reddit_recovery;
    private String reddit_media;
    private String presskit;
    private String article_link;
    private String wikipedia;
    private String video_link;
    private String youtube_id;
    private List<String> flickr_images;
}
