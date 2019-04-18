package diego.reloj.com.reloj.Views;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import diego.reloj.com.reloj.R;
import diego.reloj.com.reloj.Adapters.ViewPageAdapter;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tablayout);
        ViewPager viewPager = findViewById(R.id.viewpag);
        loadViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_reloj);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_hora_mundial);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_temporizador);

    }

    private void loadViewPager(ViewPager viewPager) {
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new RelojFragment());
        adapter.addFragment(new HoraMundialFragment());
        adapter.addFragment(new TemporizadorFragment()

        );
        viewPager.setAdapter(adapter);
    }
}
