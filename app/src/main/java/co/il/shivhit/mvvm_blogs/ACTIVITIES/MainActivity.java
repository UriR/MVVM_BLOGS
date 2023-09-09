package co.il.shivhit.mvvm_blogs.ACTIVITIES;

import androidx.lifecycle.Observer;
import co.il.shivhit.model.BlogPosts;
import co.il.shivhit.mvvm_blogs.R;
import co.il.shivhit.viewmodel.BlogsViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity
{
    private BlogsViewModel blogsLiveData;
    private BlogPosts blogPosts;
    private Button btnAddPost;
    private Button btnPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blogsLiveData = new BlogsViewModel();

        blogPosts = new BlogPosts();

        initializeViews();
        setObservers();
    }

    @Override
    protected void initializeViews() {
        btnAddPost = findViewById(R.id.btnAddPost);
        btnPosts   = findViewById(R.id.btnPosts);

        setListeners();
    }

    @Override
    protected void setListeners() {
        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BlogPostActivity.class));
            }
        });

        btnPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PostsActivity.class));
            }
        });
    }

    public void XYZ(View v){
        Toast.makeText(this, ((Button)v).getText(), Toast.LENGTH_SHORT).show();
    }

    private void setObservers(){
        blogsLiveData.getAll().observe(this, new Observer<BlogPosts>() {
            @Override
            public void onChanged(BlogPosts obsevableBlogPosts) {
                if (obsevableBlogPosts != null){
                    blogPosts = obsevableBlogPosts;
                    if (blogPosts.size() > 0) {
                        if (blogPosts.get(0) != null){
                            //txtKehila.setText(blogPosts.get(0).getContent());
                        }
                    }
                }
            }
        });
    }
}