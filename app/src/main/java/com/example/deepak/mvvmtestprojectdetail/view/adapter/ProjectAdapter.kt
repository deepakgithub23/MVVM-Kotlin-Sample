package com.example.deepak.mvvmtestprojectdetail.view.adapter

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.util.DiffUtil.calculateDiff
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.deepak.mvvmtestprojectdetail.R
import com.example.deepak.mvvmtestprojectdetail.databinding.ProjectListItemBinding
import com.example.deepak.mvvmtestprojectdetail.service.model.Project
import com.example.deepak.mvvmtestprojectdetail.view.callback.ProjectClickCallback
import java.util.*

class ProjectAdapter : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    var projectClickCallback: ProjectClickCallback? = null
    private var binding: ProjectListItemBinding? = null
    internal var projectList: List<Project>? = null


    constructor(projectClickCallback: ProjectClickCallback) {
        this.projectClickCallback = projectClickCallback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.project_list_item, parent, false)
        binding!!.setCallback(projectClickCallback)
        return ProjectViewHolder(binding!!)
    }

    override fun getItemCount(): Int {
        return if (projectList == null) 0 else projectList!!.size
    }

    fun setProjectList(projectList: List<Project>) {
        if (this.projectList == null) {
            this.projectList = projectList
            notifyItemRangeInserted(0, projectList.size)
        } else {
            val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return this@ProjectAdapter.projectList!!.get(oldItemPosition).id == projectList.get(newItemPosition).id
                }

                override fun getOldListSize(): Int {
                    return this@ProjectAdapter.projectList!!.size
                }

                override fun getNewListSize(): Int {
                    return projectList.size
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val project: Project = projectList.get(newItemPosition)
                    val old: Project = projectList.get(oldItemPosition)

                    return project == old && Objects.equals(project.git_url, old.git_url)
                }

            })

            this.projectList = projectList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.binding!!.setProject(projectList!!.get(position))
        holder.binding!!.executePendingBindings()
    }


    @SuppressLint("StaticFieldLeak")
    class ProjectViewHolder : RecyclerView.ViewHolder {

        var binding: ProjectListItemBinding?

        constructor(binding: ProjectListItemBinding) : super(binding.getRoot()) {
            this.binding = binding

        }

    }
}