package com.versatilogics.apps.mvvm_advanced.data.models

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id") var id: Int?,
    @SerializedName("title") var title: String?,
    @SerializedName("video_description") var videoDescription: String?,
    @SerializedName("video_views") var videoViews: Int?,
    @SerializedName("video_link") var videoLink: String?,
    @SerializedName("channel_id") var channelId: Int?,
    @SerializedName("channelid") var channelid: String?,
    @SerializedName("image_url") var imageUrl: String?,
    @SerializedName("uploaded_at") var uploadedAt: String?,
    @SerializedName("channel_logo") var channelLogo: String?,
    @SerializedName("category") var category: String?,
    @SerializedName("rank") var rank: Int?,
    @SerializedName("channel") var channel: Channel?
)