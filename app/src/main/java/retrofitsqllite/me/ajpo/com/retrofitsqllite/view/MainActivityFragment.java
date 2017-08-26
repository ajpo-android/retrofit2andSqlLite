package retrofitsqllite.me.ajpo.com.retrofitsqllite.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofitsqllite.me.ajpo.com.retrofitsqllite.R;
import retrofitsqllite.me.ajpo.com.retrofitsqllite.model.UserVO;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {

    }

    TextView txtResults;
    RecyclerView rvUsers;
    RVAdapter rvAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        txtResults = (TextView) root.findViewById(R.id.txtResults);
        rvUsers = (RecyclerView) root.findViewById(R.id.rvUsers);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvUsers.setLayoutManager(llm);
        rvAdapter = new RVAdapter();
        rvUsers.setAdapter(rvAdapter);
        rvUsers.setHasFixedSize(true);
        return root;
    }

    public void updateResult(CharSequence sb) {
        txtResults.setText(sb);
    }

    public void updateUsers(List<UserVO> lu) {
        this.rvAdapter.updateUsers(lu);
    }
}

/**
 *
 */
class RVAdapter extends RecyclerView.Adapter<RVAdapter.UserVH> {

    List<UserVO> lstU = new ArrayList<>();

    public RVAdapter(){

    }

    @Override
    public UserVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_user, parent, false);
        UserVH uvh = new UserVH(v);
        return uvh;
    }

    @Override
    public void onBindViewHolder(UserVH holder, int p) {
        holder.personName.setText(lstU.get(p).getName());
        holder.personLastName.setText(lstU.get(p).getLast_name());
    }

    @Override
    public int getItemCount() {
        return lstU.size();
    }

    public void updateUsers(List<UserVO> lst){
        this.lstU.addAll(lst);
        this.notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class UserVH extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personLastName;
        ImageView personPhoto;

        UserVH(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            personName = (TextView) itemView.findViewById(R.id.person_name);
            personLastName = (TextView) itemView.findViewById(R.id.person_last_name);
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
        }
    }
}