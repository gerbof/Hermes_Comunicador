package hermes.laboratorio.hermesbofsivori;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;


public class GrillaImagenes extends Fragment {

    private GridViewImageAdapter adapter;
    private GridView gridView;
    private static ArrayList<ImageItem> imagenesEstablo, imagenesPista, imagenesNecesidades, imagenesEmociones, imagenesAlumno;

    public GrillaImagenes() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        imagenesEstablo = incializarImagenes(R.array.establo_image_ids,this.getActivity());
        imagenesPista = incializarImagenes(R.array.pista_image_ids,this.getActivity());
        imagenesNecesidades = incializarImagenes(R.array.necesidades_image_ids,this.getActivity());
        imagenesEmociones = incializarImagenes(R.array.emociones_image_ids,this.getActivity());
        imagenesAlumno = incializarImagenes(R.array.example_alumn,this.getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_grilla_pista, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);

        Bundle bundle = getArguments();

        switch (bundle.getInt("tab")){
            case 0:
                adapter = new GridViewImageAdapter(this.getActivity(), R.layout.grid_item_layout, imagenesPista);
                break;
            case 1:
                adapter = new GridViewImageAdapter(this.getActivity(), R.layout.grid_item_layout, imagenesEstablo);
                break;
            case 2:
                adapter = new GridViewImageAdapter(this.getActivity(), R.layout.grid_item_layout, imagenesNecesidades);
                break;
            case 3:
                adapter = new GridViewImageAdapter(this.getActivity(), R.layout.grid_item_layout, imagenesEmociones);
                break;
            case 4:
                adapter = new GridViewImageAdapter(this.getActivity(), R.layout.grid_item_layout, imagenesAlumno);
                break;
        }

        if (bundle.getInt("tab")!=4){


            gridView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            gridView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

                private Menu menu;

                @Override
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    getActivity().getMenuInflater().inflate(R.menu.contextual_menu, menu);
                    actionMode.setTitle("Seleccionar");
                    setSubtitle(actionMode);
                    this.menu = menu;
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.add:
                            add();
                            actionMode.finish(); // Action picked, so close the CAB
                            return true;
                        case R.id.remove:
                            remove();
                            actionMode.finish(); // Action picked, so close the CAB
                            return true;
                        case R.id.subtract:
                            subtract();
                            actionMode.finish(); // Action picked, so close the CAB
                            return true;
                        default:
                            return false;
                    }
                }

                @Override
                public void onDestroyActionMode(ActionMode actionMode) {

                }

                @Override
                public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                    setSubtitle(actionMode);
                }
            });
        }

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibrator.vibrate(100);
                view.setSelected(true);
            }
        });

        return view;
    }

    private void subtract() {
        System.out.println("Boton presionado");
    }

    private void remove() {
        System.out.println("Boton presionado");
    }

    private void add() {
        System.out.println("Boton presionado");
    }

    protected void setSubtitle(ActionMode mode) {
        int checkedCount = gridView.getCheckedItemCount();
        switch (checkedCount) {
            case 0:
                mode.setSubtitle(null);
                break;
            case 1:
                mode.setSubtitle("Un item seleccionado");
                break;
            default:
                mode.setSubtitle("" + checkedCount + " items seleccionados");
                break;
        }
    }

    public static ArrayList<ImageItem> incializarImagenes(int source, Activity act){
        final ArrayList<ImageItem> imageItems = new ArrayList<ImageItem>();
        TypedArray imgs = act.getResources().obtainTypedArray(source);
        for (int i = 0; i < imgs.length(); i++) {
            Drawable drawable = act.getResources().getDrawable(imgs.getResourceId(i, -1));

            ImageView bitmap = new ImageView(act);
            bitmap.setImageResource(imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(imgs.getResourceId(i, -1), String.copyValueOf(imgs.getString(i).toCharArray(),13, (imgs.getString(i).length()-3-14)) ));
        }
        return imageItems;
    }
}
