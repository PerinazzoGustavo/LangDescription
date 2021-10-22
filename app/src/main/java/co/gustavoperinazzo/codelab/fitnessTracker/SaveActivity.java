package co.gustavoperinazzo.codelab.fitnessTracker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SaveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        RecyclerView rv_save = findViewById(R.id.rv_list);

        Bundle extras = getIntent().getExtras();
        // new thread
        if (extras != null) {
            String type = extras.getString("type");

            new Thread(() -> {
                List<Register> registers = SqlHelper.getInstance(this).getRegisterBy(type);

                /* Roda o primeiro código na thread de código*/
                runOnUiThread(() -> {
                    Log.d("teste de registros", registers.toString());

                    /* Set do adapter */
                    ListAdapter adapter = new ListAdapter(registers);
                    rv_save.setLayoutManager(new LinearLayoutManager(this));
                    rv_save.setAdapter(adapter);
                });


            }).start();
        }


    }

    private class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

        private List<Register> registersList;

        private ListAdapter(List<Register> registersList) {
            this.registersList = registersList;
        }


        @NonNull
        @Override
        public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ListViewHolder(getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
            Register currentRegister = registersList.get(position);
            holder.bind(currentRegister);
        }

        @Override
        public int getItemCount() {
            return registersList.size();
        }

        private class ListViewHolder extends RecyclerView.ViewHolder {

            public ListViewHolder(@NonNull View itemView) {
                super(itemView);
            }

            public void bind(Register register) {
                String formatted = "";
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("pt", "br"));
                    Date dateSaved = sdf.parse(register.created_date);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "br"));
                    formatted = dateFormat.format(dateSaved);

                    String actual_hour = sdf.format(new Date());
                } catch (ParseException e) {
                }



                /* Casting do objeto que já é uma view */
                ((TextView) itemView).setText(
                        getString(R.string.save_response, register.response, formatted)
                );
            }
        }
    }
}