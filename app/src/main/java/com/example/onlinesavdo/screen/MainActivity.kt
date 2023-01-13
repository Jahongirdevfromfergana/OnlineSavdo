package com.example.onlinesavdo.screen

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.onlinesavdo.R
import com.example.onlinesavdo.databinding.ActivityMainBinding
import com.example.onlinesavdo.screen.cart.CartFragment
import com.example.onlinesavdo.screen.changelanguage.ChangeLanguageFragment
import com.example.onlinesavdo.screen.favorite.FavoriteFragment
import com.example.onlinesavdo.screen.home.HomeFragment
import com.example.onlinesavdo.screen.user.UserFragment
import com.example.onlinesavdo.utils.LocaleManager
import com.onesignal.OneSignal
import io.reactivex.Observable

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val ONESIGNAL_APP_ID = "8fd118f4-5eeb-485d-8c7c-cccafb989191"

    val homeFragment = HomeFragment.newInstance()
    val favoriteFragment = FavoriteFragment.newInstance()
    val cartFragment = CartFragment.newInstance()
    val userFragment = UserFragment.newInstance()

    var activeFragment: Fragment = homeFragment

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = MainViewModel()


        viewModel.productData.observe(this, Observer {
           viewModel.insertAllProducts2DB(it)
        })
        viewModel.categoryData.observe(this, Observer {
            viewModel.insertAllCategories2DB(it)
        })

        viewModel.errorData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications();
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, homeFragment, homeFragment.tag).hide(homeFragment).commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, favoriteFragment, favoriteFragment.tag).hide(favoriteFragment).commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, cartFragment, cartFragment.tag).hide(cartFragment).commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, userFragment, userFragment.tag).hide(userFragment).commit()

        supportFragmentManager.beginTransaction().show(activeFragment).commit()

        binding.bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId == R.id.actionHome) {
                supportFragmentManager.beginTransaction()
                    .hide(activeFragment).show(homeFragment).commit()
                activeFragment = homeFragment
            } else if (it.itemId == R.id.actionFavorite) {
                supportFragmentManager.beginTransaction()
                    .hide(activeFragment).show(favoriteFragment).commit()
                activeFragment = favoriteFragment
            } else if (it.itemId == R.id.actionCart) {
                supportFragmentManager.beginTransaction()
                    .hide(activeFragment).show(cartFragment).commit()
                activeFragment = cartFragment
            } else if (it.itemId == R.id.actionUser) {
                supportFragmentManager.beginTransaction()
                    .hide(activeFragment).show(userFragment).commit()
                activeFragment = userFragment
            }
            return@setOnItemSelectedListener true
        }

        binding.btnMenu.setOnClickListener {
            var fragment = ChangeLanguageFragment.newInstance()
            fragment.show(supportFragmentManager, fragment.tag)

        }
        loadData()
    }
    fun loadData(){
        viewModel.getTopProducts()
        viewModel.getCategories()

    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleManager.setLocale(newBase))
    }
}