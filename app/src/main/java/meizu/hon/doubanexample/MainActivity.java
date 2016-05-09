package meizu.hon.doubanexample;

import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import meizu.hon.doubanexample.databinding.ActivityMainBinding;
import meizu.hon.doubanexample.ui.base.BaseActivity;
import meizu.hon.doubanexample.ui.fragment.AboutFragment;
import meizu.hon.doubanexample.ui.fragment.BackHandledFragment;
import meizu.hon.doubanexample.ui.fragment.DoubanFragment;
import meizu.hon.doubanexample.ui.fragment.GithubFragment;
import meizu.hon.doubanexample.ui.fragment.MusicFragment;
import meizu.hon.doubanexample.viewmodel.ViewModel;

public class MainActivity extends BaseActivity<ViewModel, ActivityMainBinding> implements BackHandledFragment.BackHandlerInterface {

    private BackHandledFragment mSelectedFragment;

    @Override protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override protected void initView() {
        setSupportActionBar(getBinding().appbar.toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, getBinding().drawerLayout,
                getBinding().appbar.toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        getBinding().drawerLayout.addDrawerListener(drawerToggle);
        setupDrawerContent();
        switchToDouban();

    }

    private void setupDrawerContent() {
        getBinding().navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navi_item_douban:
                    switchToDouban();
                    break;
                case R.id.navi_item_music:
                    switchToMusic();
                    break;
                case R.id.navi_item_github:
                    switchToGithub();
                    break;
                case R.id.navi_item_about:
                    switchToAbout();
                    break;
            }
            item.setChecked(true);
            getBinding().drawerLayout.closeDrawers();
            return true;
        });
    }

    private void switchToDouban() {
        replaceFragment(R.id.frame_content, new DoubanFragment(), "douban");
        setTitle("Douban");
    }

    private void switchToMusic() {
        replaceFragment(R.id.frame_content, new MusicFragment(), "music");
        setTitle("music");
    }

    private void switchToGithub() {
        replaceFragment(R.id.frame_content, new GithubFragment(), "github");
        setTitle("Github");
    }

    private void switchToAbout() {
        replaceFragment(R.id.frame_content, new AboutFragment(), "about");
        setTitle("About");
    }

    @Override public void setSelectedFragment(BackHandledFragment backHandledFragment) {
        this.mSelectedFragment = backHandledFragment;
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (mSelectedFragment == null || !mSelectedFragment.onBackPressed()) {
            if (getBinding().drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                getBinding().drawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                doExitApp();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
