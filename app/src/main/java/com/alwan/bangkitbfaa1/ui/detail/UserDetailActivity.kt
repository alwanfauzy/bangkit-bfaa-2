package com.alwan.bangkitbfaa1.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alwan.bangkitbfaa1.R
import com.alwan.bangkitbfaa1.data.User
import com.alwan.bangkitbfaa1.databinding.ActivityUserDetailBinding
import com.alwan.bangkitbfaa1.util.loadImage

class UserDetailActivity : AppCompatActivity() {
    private var _binding: ActivityUserDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.user_detail)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        populateDetail(user)
    }

    private fun populateDetail(user: User) {
        with(binding) {
            imgDetailAvatar.loadImage(user.avatar)
            tvDetailName.text = user.name
            tvDetailUsername.text = user.username
            tvDetailFollower.text = user.follower
            tvDetailFollowing.text = user.following
            tvDetailCompany.text = user.company
            tvDetailLocation.text = user.location
            tvDetailRepository.text = user.repository
        }
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}