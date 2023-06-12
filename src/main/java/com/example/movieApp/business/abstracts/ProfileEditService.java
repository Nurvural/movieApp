package com.example.movieApp.business.abstracts;

import com.example.movieApp.business.requests.UpdateProfileRequest;
import com.example.movieApp.core.utilities.results.Result;

public interface ProfileEditService {

	Result update(UpdateProfileRequest updateProfileRequest, int id) throws Exception;
}
