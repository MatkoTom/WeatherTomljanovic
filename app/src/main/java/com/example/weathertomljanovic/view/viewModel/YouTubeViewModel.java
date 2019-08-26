package com.example.weathertomljanovic.view.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.weathertomljanovic.model.youTube.Items;
import com.example.weathertomljanovic.model.youTube.YouTubeResponse;
import com.example.weathertomljanovic.repositories.YouTubeRepository;
import com.example.weathertomljanovic.utils.Const;
import com.example.weathertomljanovic.utils.helpers.SingleLiveEvent;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class YouTubeViewModel extends ViewModel {

    private YouTubeRepository youTubeRepository;

    private SingleLiveEvent<List<Items>> setItems = new SingleLiveEvent<>();

    public YouTubeViewModel() {
        youTubeRepository = new YouTubeRepository();
    }

    public SingleLiveEvent<List<Items>> getVideoItem() {
        return setItems;
    }

    public void getVideoId(String key, String part, String maxResults, String query) {
        youTubeRepository.getVideoId(key, part, maxResults, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<YouTubeResponse>() {
                    @Override
                    public void onSuccess(YouTubeResponse youTubeResponse) {
                        getVideoItem().setValue(youTubeResponse.items);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void getId(String query) {
        getVideoId(Const.Api.YOUTUBE_API_KEY, Const.NetworkQuery.YOUTUBE_PART, "1", query);
    }
}
