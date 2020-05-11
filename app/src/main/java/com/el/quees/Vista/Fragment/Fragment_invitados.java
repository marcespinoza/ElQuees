package com.el.quees.Vista.Fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.el.quees.Adapters.QuestionAdapter;
import com.el.quees.Interface.Invitados_interface;
import com.el.quees.Interface.Question_interface;
import com.el.quees.Pojo.Question;
import com.el.quees.Presentador.Invitados_presentador;
import com.el.quees.Presentador.Question_presentador;
import com.el.quees.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_invitados extends Fragment implements Invitados_interface.Vista {

    @BindView(R.id.recycler_question)
    RecyclerView recyclerView;
    public Invitados_interface.Presentador iPresentador;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.bind(this, view);
        iPresentador = new Invitados_presentador(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            checkPermission();
        }
        iPresentador.sendRequestContacts();
    }


    private boolean checkPermission() {
        int result;
            result = ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.READ_CONTACTS);
            if (result != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 1 );
                return false;
            }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:{
                if (grantResults.length > 0) {
                        if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                            showDialogNotCancelable("Permissions mandatory",
                                    "All the permissions are required for this app",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            checkPermission();
                                        }
                                    });
                        }
                }
                return;
            }

        }
    }

    private void showDialogNotCancelable(String title, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setCancelable(false)
                .create()
                .show();
    }

}
