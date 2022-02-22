package com.alwan.bangkitbfaa1.ui.main

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwan.bangkitbfaa1.R
import com.alwan.bangkitbfaa1.data.User
import com.alwan.bangkitbfaa1.databinding.ActivityMainBinding
import com.alwan.bangkitbfaa1.ui.detail.UserDetailActivity

class MainActivity : AppCompatActivity(), UserAdapter.UserCallback {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val userAdapter = UserAdapter(this)
    private lateinit var dataUsername: Array<String>
    private lateinit var dataName: Array<String>
    private lateinit var dataAvatar: TypedArray
    private lateinit var dataFollower: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepository: Array<String>
    private val users = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepare()
        addItem()
        setupRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun prepare() {
        dataUsername = resources.getStringArray(R.array.username)
        dataName = resources.getStringArray(R.array.name)
        dataAvatar = resources.obtainTypedArray(R.array.avatar)
        dataFollower = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
        dataCompany = resources.getStringArray(R.array.company)
        dataLocation = resources.getStringArray(R.array.location)
        dataRepository = resources.getStringArray(R.array.repository)
    }

    private fun addItem() {
        users.clear()
        for (position in dataName.indices) {
            val user = User(
                dataUsername[position],
                dataName[position],
                dataAvatar.getResourceId(position, -1),
                dataFollower[position] + " Followers",
                dataFollowing[position] + " Following",
                dataCompany[position],
                dataLocation[position],
                dataRepository[position],
            )
            users.add(user)
        }
        userAdapter.setData(users)
    }

    private fun setupRecyclerView() {
        with(binding) {
            rvUsers.setHasFixedSize(true)
            rvUsers.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUsers.adapter = userAdapter
        }
    }

    override fun onUserClick(user: User) {
        val userDetailIntent = Intent(this, UserDetailActivity::class.java)
        userDetailIntent.putExtra(UserDetailActivity.EXTRA_USER, user)
        startActivity(userDetailIntent)
    }
}