package co.gustavoperinazzo.codelab.fitnessTracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    private View btnImc;
    private RecyclerView rvMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  btnImc = findViewById(R.id.btn_imc);

        // Recicler view

        /* Main itens array com itens dinâmicos, que para cada view recicladas podem ter diferentes
         * funcinaliades, instanciando a lista no adaptador e passando os parametros de cada view
         *  desejado */
        rvMain = findViewById(R.id.main_rv);

        ArrayList<MainItem> mainItems = new ArrayList<>();
        mainItems.add(new MainItem(1, R.drawable.ic_imc, R.string.label_imc,
                Color.WHITE));
        mainItems.add(new MainItem(2, R.drawable.ic_tmb, R.string.label_tmb,
                Color.WHITE));

        /* Formato do loyaut ->
         *   Linear (Horizontal/Vertical)
         *   Mosaico -> galerias
         *   Grid1 */
        // 1° - Definir o comportamento de exibição do layout reciclado
        rvMain.setLayoutManager(new GridLayoutManager(this, 2));
        // Setar um adapter numa lista dinâmica
        MainAdapter adapter = new MainAdapter(mainItems);
        adapter.setListener(id -> {
            // Intenção de abrir uma segunda tela ->
            switch (id) {
                case 1:
                    startActivity(new Intent(MainActivity.this, ImcActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this, TmbActivity.class));
                    break;
            }
        });
        rvMain.setAdapter(adapter);

    }


    /* Adapter para conectar as views recicladas */
    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

        private List<MainItem> mainItems;
        private OnItemClickListener listener;

        public MainAdapter(List<MainItem> mainItems) {
            this.mainItems = mainItems;
        }

        public void setListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(getLayoutInflater().inflate(R.layout.main_item,
                    parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            /* Toda vez que o programa rolar para próxima view reciclada ele irá buscar
             * a posição dessa view no holder(célula) */
            MainItem mainItemCurrent = mainItems.get(position);
            holder.bind(mainItemCurrent);


        }

        @Override
        // Quantidade de views a serem recicladas
        public int getItemCount() {
            return mainItems.size();
        }

        // Pega as referencias especificas de cada layout, View da célula que está dentro do recycler
        // view
        private class MainViewHolder extends RecyclerView.ViewHolder {
            public MainViewHolder(@NonNull View itemView) {
                super(itemView); // -> container principal
            }

            public void bind(MainItem item) {
                TextView imcTxtName = itemView.findViewById(R.id.item_imc_txt);
                ImageView imcImgName = itemView.findViewById(R.id.item_imc_img);
                LinearLayout btnImc = (LinearLayout) itemView.findViewById(R.id. btn_imc); // casting


                btnImc.setOnClickListener(view -> {
                    // toda vez que a tela for tocada ele irá chamar o método listener
                    listener.onClick(item.getId());
                });

                /*
                 * Set das propriedades de cada viewReciclada
                 * Depois de pegar as posições(holder) ele irá pegar as informações dentro da função de
                 * bind passando o texto, a imagem e o background*/

                imcTxtName.setText(item.getTextStringId());
                imcImgName.setImageResource(item.getDrawableId());
                btnImc.setBackgroundColor(item.getItemBackground());
            }
        }
    }


}