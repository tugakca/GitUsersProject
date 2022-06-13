package com.tmob.casestudy.model

import java.io.Serializable

data class UserListResponseItem(
    val avatar_url: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    val id: Int,
    val login: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String,
    var followingSize:Int?=null,
    var followersSize:Int?=null,
    var repoSize:Int?=null,
    var starredSize:Int?=null,
    var orgSize:Int?=null,
):Serializable