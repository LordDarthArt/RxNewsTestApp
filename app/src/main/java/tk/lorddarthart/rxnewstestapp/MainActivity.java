package tk.lorddarthart.rxnewstestapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainMainFragment.OnFragmentInteractionListener, MainVipFragment.OnFragmentInteractionListener {

    private String responseText;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_main:
                    initializeFragment(MainFragment.newInstance());
                    return true;
                case R.id.navigation_search:
                    initializeFragment(new SearchFragment());
                    return true;
                case R.id.navigation_notifications:
                    initializeFragment(new NotificationsFragment());
                    return true;
                case R.id.navigation_account:
                    initializeFragment(new AccountFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_main);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Получаем ответ - строку от сервера
    }

    void initializeFragment(Fragment fr) {
        // Инициализация кастомного фрагмента
        FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
        fragmentManager.replace(R.id.mainFragment, fr);
        fragmentManager.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
