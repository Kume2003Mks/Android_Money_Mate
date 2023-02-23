package com.example.moneymate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.moneymate.inmoneymenu.MoneyDB;
import com.example.moneymate.intodomenu.todoDB;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        MoneyDB Mdb;
        todoDB Tdb;
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            Mdb = new MoneyDB(getContext());
            Tdb = new todoDB(getContext());

            Preference Del_all_DB = findPreference("clear_all_data");
            assert Del_all_DB != null;
            Del_all_DB.setOnPreferenceClickListener(preference -> {
                clear_all_data();
                return true;
            });

            Preference Del_todo_DB = findPreference("clear_todo_data");
            assert Del_todo_DB != null;
            Del_todo_DB.setOnPreferenceClickListener(preference -> {
                clear_ToDo_data();
                return true;
            });

            Preference Del_money_DB = findPreference("clear_money_data");
            assert Del_money_DB != null;
            Del_money_DB.setOnPreferenceClickListener(preference -> {
                clear_money_data();
                return true;
            });

        }
        private void clear_all_data() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Do you want to delete all data?");
            builder.setPositiveButton("yes", (dialogInterface, i) -> {
                Mdb.del_all_data();
                Tdb.del_all_data();
                Toast.makeText(getContext(), "Clear Complete", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), MainActivity.class));
            });
            builder.setNegativeButton("No", (dialogInterface, i) -> {

            });
            builder.create().show();
        }

        private void clear_ToDo_data() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Do you want to delete TODO data?");
            builder.setPositiveButton("yes", (dialogInterface, i) -> {
                Tdb.del_all_data();
                Toast.makeText(getContext(), "Clear Complete", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), MainActivity.class));
            });
            builder.setNegativeButton("No", (dialogInterface, i) -> {

            });
            builder.create().show();
        }

        private void clear_money_data() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Do you want to delete Money data?");
            builder.setPositiveButton("yes", (dialogInterface, i) -> {
                Mdb.del_all_data();
                Toast.makeText(getContext(), "Clear Complete", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), MainActivity.class));
            });
            builder.setNegativeButton("No", (dialogInterface, i) -> {

            });
            builder.create().show();
        }
    }
}