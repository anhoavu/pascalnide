/*
 *  Copyright 2017 Tran Le Duy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duy.pascal.frontend.code_sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.commonsware.cwac.pager.PageDescriptor;
import com.commonsware.cwac.pager.SimplePageDescriptor;
import com.duy.pascal.frontend.BuildConfig;
import com.duy.pascal.frontend.R;
import com.duy.pascal.frontend.activities.AbstractAppCompatActivity;
import com.duy.pascal.frontend.activities.ExecuteActivity;
import com.duy.pascal.frontend.code.CompileManager;
import com.duy.pascal.frontend.code_editor.EditorActivity;
import com.duy.pascal.frontend.file.ApplicationFileManager;
import com.lapism.searchview.SearchHistoryTable;
import com.lapism.searchview.SearchItem;
import com.lapism.searchview.SearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CodeSampleActivity extends AbstractAppCompatActivity implements CodeSampleAdapter.OnCodeClickListener {

    final String TAG = getClass().getSimpleName();

    private final String[] categories;

    //    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.search_view)
    SearchView searchView;
    //    @BindView(R.id.drawer_layout)
//    DrawerLayout drawerLayout;
    private ApplicationFileManager fileManager;
    private SearchHistoryTable mHistoryDatabase;
    private CodePagerAdapter pagerAdapter;

    public CodeSampleActivity() {
        if (BuildConfig.DEBUG) {
            categories = new String[]{"Temp", "Basic", "System", "Crt", "Dos", "Graph", "Math", "Android", "More"};
        } else {
            categories = new String[]{"Basic", "System", "Crt", "Dos", "Graph", "Math", "Android", "More"};
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileManager = new ApplicationFileManager(getApplicationContext());
        setContentView(R.layout.activity_code_sample);
        ButterKnife.bind(this);

        final ArrayList<PageDescriptor> pages = new ArrayList<>();
        for (String category : categories) {
            pages.add(new SimplePageDescriptor(category, category));
        }
        pagerAdapter = new CodePagerAdapter(getSupportFragmentManager(), pages);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        mHistoryDatabase = new SearchHistoryTable(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mHistoryDatabase.addItem(new SearchItem(query));
                searchView.close(true);
                FragmentCodeSample fragmentCodeSample = pagerAdapter.getCurrentFragment();
                if (fragmentCodeSample != null) {
                    fragmentCodeSample.query(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnMenuClickListener(new SearchView.OnMenuClickListener() {
            @Override
            public void onMenuClick() {

            }
        });
    }

//    private void openDrawer() {
//        drawerLayout.openDrawer(GravityCompat.START);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onPlay(String code) {
        //create file temp
        fileManager.setContentFileTemp(code);

        //set file temp for run
        Intent intent = new Intent(this, ExecuteActivity.class);

        //this code is verified, do not need compile
        intent.putExtra(CompileManager.FILE_PATH, fileManager.getTempFile().getPath());
        startActivity(intent);
    }

    @Override
    public void onEdit(String code) {
        //create file temp
        String file = fileManager.createNewFileInMode("sample_" + Integer.toHexString((int) System.currentTimeMillis()) + ".pas");
        fileManager.saveFile(file, code);

        //set file temp for run
        Intent intent = new Intent(this, EditorActivity.class);

        //this code is verified, do not need compile
        intent.putExtra(CompileManager.FILE_PATH, file);
        startActivity(intent);
    }


}