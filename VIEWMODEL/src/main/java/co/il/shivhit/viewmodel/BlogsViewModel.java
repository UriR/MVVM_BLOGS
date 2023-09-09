package co.il.shivhit.viewmodel;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import co.il.shivhit.helper.StringUtil;
import co.il.shivhit.model.BlogPost;
import co.il.shivhit.model.BlogPosts;
import co.il.shivhit.repository.BlogsRepository;

public class BlogsViewModel extends ViewModel {
    private BlogsRepository repository;
    private MutableLiveData<BlogPosts> blogPostsLiveDAta;

    private MutableLiveData<Boolean>   successOperation;

    public BlogsViewModel() {
        repository = new BlogsRepository();
        blogPostsLiveDAta = new MutableLiveData<>();

        successOperation = new MutableLiveData<>();
    }

    public void save(BlogPost blogPost){
        if (StringUtil.isNullOrEmpty(blogPost.getIdFs()))
            add(blogPost);
        else
            update(blogPost);
    }

    public void add(BlogPost blogPost){
        repository.add(blogPost)
                .addOnSuccessListener(aBoolean -> {successOperation.setValue(true);})
                .addOnFailureListener(e -> {successOperation.setValue(false);});

                /*
                .addOnSuccessListener(new OnSuccessListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        successOperation.setValue(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        successOperation.setValue(false);
                    }
                });
                 */
        //successOperation.setValue(true);
    }

    public void update(BlogPost blogPost){
        repository.update(blogPost)
                .addOnSuccessListener(aBoolean -> {successOperation.setValue(true);})
                .addOnFailureListener(e -> {successOperation.setValue(false);});
    }

    public LiveData<Boolean> getSuccessOperation(){
        return successOperation;
    }

    public LiveData<BlogPosts> getAll(){
        blogPostsLiveDAta = repository.getAll();
        return blogPostsLiveDAta;
    }
}