package cn.lovexiaov.f0t1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import cn.lovexiaov.f0t1.ui.basic.BasicFragment;
import cn.lovexiaov.f0t1.ui.basic.dummy.DummyContent;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener,
    BasicFragment.OnListFragmentInteractionListener {

  private DrawerLayout drawer;
  private ActionBarDrawerToggle toggle;

  private FragmentManager fragmentManager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();

    fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction()
        .add(R.id.content_main, BasicFragment.newInstance(1))
        .commit();
  }

  private void initView() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
        R.string.navigation_drawer_close);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });

    drawer.addDrawerListener(toggle);
    toggle.syncState();

    navigationView.setNavigationItemSelectedListener(this);
    navigationView.setCheckedItem(R.id.basic);
  }

  @Override public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    } else if (id == R.id.view) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody") @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.basic) {
      Toast.makeText(this, "Hi Android!", Toast.LENGTH_SHORT).show();
    }

    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override protected void onDestroy() {
    drawer.removeDrawerListener(toggle);
    super.onDestroy();
  }

  @Override public void onListFragmentInteraction(DummyContent.DummyItem item) {
    Toast.makeText(this, item.content, Toast.LENGTH_SHORT).show();
  }
}
