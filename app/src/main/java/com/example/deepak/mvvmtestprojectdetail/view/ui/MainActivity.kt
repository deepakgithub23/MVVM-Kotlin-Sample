package com.example.deepak.mvvmtestprojectdetail.view.ui

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.example.deepak.mvvmtestprojectdetail.R
import com.example.deepak.mvvmtestprojectdetail.service.model.Project

class MainActivity: AppCompatActivity(), ProjectFragment.MyInterface {
    override fun onTrigger() {
        Toast.makeText(this, "Called", Toast.LENGTH_LONG).show()
    }

    var toolbar:Toolbar? = null
    var fragment:Fragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            var fragment: ProjectListFragment = ProjectListFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment, ProjectListFragment.TAG).commit()
        }
    }
    fun show(project: Project): Unit {
            var projectFragment: ProjectFragment = ProjectFragment.forProject(project.name)
            supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("project")
                    .add(R.id.fragment_container, projectFragment, null)
                    .commit()
    }

    }