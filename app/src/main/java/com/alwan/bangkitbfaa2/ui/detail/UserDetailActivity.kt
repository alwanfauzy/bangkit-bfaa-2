package com.alwan.bangkitbfaa2.ui.detail

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alwan.bangkitbfaa2.R
import com.alwan.bangkitbfaa2.data.model.User
import com.alwan.bangkitbfaa2.data.model.UserDetail
import com.alwan.bangkitbfaa2.databinding.ActivityUserDetailBinding
import com.alwan.bangkitbfaa2.ui.detail.follow.FollowPagerAdapter
import com.alwan.bangkitbfaa2.util.loadImage
import com.google.android.material.tabs.TabLayoutMediator

class UserDetailActivity : AppCompatActivity() {
    private var _binding: ActivityUserDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.user_detail)
        supportActionBar?.elevation = 0f

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        user.login?.let { setupViewModel(it) }
        setupViewPager()
    }

    private fun populateDetail(user: UserDetail) {
        with(binding) {
            imgDetailAvatar.loadImage(user.avatarUrl)
            tvDetailName.text = user.name ?: "-"
            tvDetailUsername.text = user.login ?: "-"
            tvDetailFollower.text = getString(R.string.detail_followers, user.followers)
            tvDetailFollowing.text = getString(R.string.detail_following, user.following)
            tvDetailCompany.text = user.company ?: "-"
            tvDetailLocation.text = user.location ?: "-"
            tvDetailRepository.text = user.publicRepos ?: "-"
        }
    }

    private fun setupViewModel(username: String) {
        showLoading(true)
        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        detailViewModel.setUserDetail(username)

        detailViewModel.userDetail.observe(this) {
            populateDetail(it)
            showLoading(false)
        }
    }

    private fun setupViewPager() {
        val pagerAdapter = FollowPagerAdapter(this)
        val viewPager = binding.viewPagerDetail
        viewPager.adapter = pagerAdapter
        val tabs = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun showLoading(state: Boolean) {
        binding.progressBarDetail.visibility = if (state) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_USER = "extra_user"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.app_follower,
            R.string.app_following
        )
    }
}