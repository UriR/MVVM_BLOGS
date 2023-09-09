package co.il.shivhit.mvvm_blogs.ACTIVITIES;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import co.il.shivhit.model.BlogPost;
import co.il.shivhit.model.BlogPosts;
import co.il.shivhit.mvvm_blogs.ADAPTERS.PostAdapter;
import co.il.shivhit.mvvm_blogs.R;
import co.il.shivhit.viewmodel.BlogsViewModel;

public class PostsActivity extends BaseActivity {
    private FloatingActionButton fabAdd;
    private RecyclerView         rvPosts;

    private BlogPosts           blogPosts;
    private PostAdapter         adapter;
    private BlogsViewModel      blogsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        blogsViewModel = new BlogsViewModel();

        initializeViews();

        blogPosts = new BlogPosts();

        BlogPost blogPost = new BlogPost();
        blogPost.setDate(10000);
        blogPost.setTitle("Title");
        blogPost.setContent("Content");
        blogPost.setAuthor("Author");
        blogPosts.add(blogPost);

        setRecyclerView();
        setObservers();

    }

    @Override
    protected void initializeViews() {
        fabAdd  = findViewById(R.id.fabAdd);
        rvPosts = findViewById(R.id.rvPosts);

        setListeners();
    }

    @Override
    protected void setListeners() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PostsActivity.this, "ADD POST", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecyclerView(){
        PostAdapter.OnItemClickListener listener = new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(BlogPost blogPost) {
                Intent intent = new Intent(PostsActivity.this, BlogPostActivity.class);
                intent.putExtra("POST", blogPost);
                startActivity(intent);
            }
        };

        PostAdapter.OnItemLongClickListener longListener = new PostAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(BlogPost blogPost) {
                Toast.makeText(PostsActivity.this, blogPost.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        adapter = new PostAdapter(this, blogPosts, R.layout.single_post_layout, listener, longListener);

        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setObservers(){
        blogsViewModel.getAll().observe(this, new Observer<BlogPosts>() {
            @Override
            public void onChanged(BlogPosts observedBlogPosts) {
                blogPosts = observedBlogPosts;
                adapter.setBlogPosts(blogPosts);
            }
        });
    }
}