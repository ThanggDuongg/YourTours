package com.hcmute.yourtours.controllers.cms.interfaces;

import com.hcmute.yourtours.libs.controller.*;
import com.hcmute.yourtours.libs.model.factory.response.BasePagingResponse;
import com.hcmute.yourtours.libs.model.factory.response.BaseResponse;
import com.hcmute.yourtours.models.amenity_categories.AmenityCategoryDetail;
import com.hcmute.yourtours.models.amenity_categories.AmenityCategoryHomeDetail;
import com.hcmute.yourtours.models.amenity_categories.AmenityCategoryInfo;
import com.hcmute.yourtours.models.amenity_categories.filter.AmenityCategoryFilter;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.UUID;

public interface ICmsAmenityCategoriesController extends
        ICreateModelController<UUID, AmenityCategoryDetail>,
        IUpdateModelController<UUID, AmenityCategoryDetail>,
        IGetDetailByIdController<UUID, AmenityCategoryDetail>,
        IGetInfoPageController<UUID, AmenityCategoryInfo, AmenityCategoryFilter>,
        IDeleteModelByIdController<UUID> {

    @GetMapping("home/{id}/detail")
    @Operation(summary = "Lấy chi tiết 1 category của tiện ích với cái thông tin có hay không trong nhà")
    ResponseEntity<BaseResponse<AmenityCategoryHomeDetail>> getDetailByIdAndHomeId(@PathVariable UUID id,
                                                                                   @RequestParam UUID homeId);


    @GetMapping("page/child")
    ResponseEntity<BaseResponse<BasePagingResponse<AmenityCategoryHomeDetail>>> getInfoPageAndChildren(
            @ParameterObject @Valid AmenityCategoryFilter filter,
            @RequestParam(defaultValue = "0") Integer number,
            @RequestParam(defaultValue = "20") Integer size
    );
}

