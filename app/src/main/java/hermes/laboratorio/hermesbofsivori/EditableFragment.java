package hermes.laboratorio.hermesbofsivori;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.GpsStatus;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EditableFragment extends Fragment {

    private GridViewImageAdapter adapter;
    private GridViewImageAdapter adapter2;
    private GridView gridView;
    private GridView gridView2;

    public EditableFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_editable2, container, false);

        gridView = (GridView) view.findViewById(R.id.grid_view2);
        gridView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibrator.vibrate(100);

                view.setSelected(true);
                view.setBackgroundColor(0x3F51B5);

                String itemName = ((ImageItem) parent.getItemAtPosition(position)).getTitle();
                if(String.copyValueOf(itemName.toCharArray(),0, itemName.length()-2).equals("establo_caballo")){
                    itemName = "establo_caballo";
                }
                else{
                    if(String.copyValueOf(itemName.toCharArray(),0, itemName.length()-1).equals("emociones_dolorid")){
                        itemName = "emociones_me_duele";
                    }
                    else {
                        if (String.copyValueOf(itemName.toCharArray(), 0, itemName.length() - 2).equals("emociones_triste")) {
                            itemName = "emociones_triste";
                        }
                        else{
                            if (String.copyValueOf(itemName.toCharArray(), 0, itemName.length() - 2).equals("necesidades_sed")) {
                                itemName = "necesidades_sed";
                            }
                        }
                    }
                }
                int idItem = getActivity().getResources().getIdentifier("raw/"+itemName,
                        "raw", getActivity().getPackageName());
                MediaPlayer mp = MediaPlayer.create(getActivity(), idItem);
                mp.start();
            }
        });
        adapter = new GridViewImageAdapter(this.getActivity(), R.layout.grid_item_layout, GrillaImagenes.incializarImagenes(R.array.example_alumn, this.getActivity()));
        gridView.setAdapter(adapter);

        gridView2 = (GridView) view.findViewById(R.id.grid_view3);
        adapter2 = new GridViewImageAdapter(this.getActivity(), R.layout.grid_item_layout2, GrillaImagenes.incializarImagenes(R.array.yes_no,this.getActivity()));
        gridView2.setAdapter(adapter2);
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibrator.vibrate(100);
                String itemName = ((ImageItem) parent.getItemAtPosition(position)).getTitle();
                int idItem = getActivity().getResources().getIdentifier("raw/" + itemName,
                        "raw", getActivity().getPackageName());
                MediaPlayer mp = MediaPlayer.create(getActivity(), idItem);
                mp.start();
            }
        });

        return view;
    }
}
