package com.example.deepak.mvvmtestprojectdetail.service.model

import java.util.*

class Project {

    var id:Int?=null
    var name:String
    var full_name:String?=null
    var owner: User?=null
    var html_url:String?=null
    var description:String?=null
    var url:String?=null
    var created_at:Date?=null
    var updated_at:Date?=null
    var pushed_at:Date?=null
    var git_url:String?=null
    var ssh_url:String?=null
    var clone_url:String?=null
    var svn_url:String?=null
    var homepage:String?=null
    var stargazers_count:Int?=null
    var watchers_count:Int?=null
    var language:String?=null
    var has_issues:Boolean?=null
    var has_downloads:Boolean?=null
    var has_wiki:Boolean?=null
    var has_pages:Boolean?=null
    var forks_count:Int?=null
    var open_issues_count:Int?=null
    var forks:Int?=null
    var open_issues:Int?=null
    var watchers: String?=null
    var defualt_branch:String? = null

   constructor(name:String){
    this.name=name
   }

}