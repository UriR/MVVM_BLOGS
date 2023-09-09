package co.il.shivhit.mvvm_blogs.ADAPTERS;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import co.il.shivhit.helper.DateUtil;
import co.il.shivhit.model.BlogPost;
import co.il.shivhit.model.BlogPosts;
import co.il.shivhit.mvvm_blogs.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {
    public interface OnItemClickListener {
        public void onItemClicked(BlogPost blogPost);
    }

    public interface OnItemLongClickListener {
        public boolean onItemLongClicked(BlogPost blogPost);
    }

    private final Context                 context;
    private BlogPosts                     blogPosts;
    private final int                     singlePostLayout;
    private final OnItemClickListener     listener;
    private final OnItemLongClickListener longListener;

    public PostAdapter(Context context, BlogPosts blogPosts, int singlePostLayout, OnItemClickListener listener, OnItemLongClickListener longListener){
        this.context          = context;
        this.blogPosts        = blogPosts;
        this.singlePostLayout = singlePostLayout;
        this.listener         = listener;
        this.longListener     = longListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setBlogPosts(BlogPosts blogPosts){
        this.blogPosts = blogPosts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(singlePostLayout, parent, false);
        return new PostHolder (itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        BlogPost blogPost = blogPosts.get(position);

        if (blogPost != null){
            holder.bind(blogPost, listener, longListener);
        }
    }

    @Override
    public int getItemCount() {
        return (blogPosts != null) ? blogPosts.size() : 0;
    }

    static class PostHolder extends RecyclerView.ViewHolder{
        public TextView txtAuthor;
        public TextView txtTitle;
        public TextView txtContent;
        public TextView txtDate;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            txtAuthor   = itemView.findViewById(R.id.txtAuthor);
            txtTitle    = itemView.findViewById(R.id.txtTitle);
            txtContent  = itemView.findViewById(R.id.txtConrent);
            txtDate     = itemView.findViewById(R.id.txtDate);
        }

        public void bind(BlogPost blogPost, OnItemClickListener listener, OnItemLongClickListener longListener){
            txtAuthor.setText(blogPost.getAuthor());
            txtTitle.setText(blogPost.getTitle());
            txtContent.setText(blogPost.getContent());
            txtDate.setText(DateUtil.longDateToString(blogPost.getDate()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(blogPost);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longListener.onItemLongClicked(blogPost);
                    return true;
                }
            });
        }
    }
}
