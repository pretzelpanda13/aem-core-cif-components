/*******************************************************************************
 *
 *    Copyright 2020 Adobe. All rights reserved.
 *    This file is licensed to you under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License. You may obtain a copy
 *    of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software distributed under
 *    the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
 *    OF ANY KIND, either express or implied. See the License for the specific language
 *    governing permissions and limitations under the License.
 *
 ******************************************************************************/
package com.adobe.cq.commerce.core.components.internal.models.v1.relatedproducts;

import java.util.Set;

import javax.annotation.Nonnull;

import org.apache.sling.api.resource.Resource;

import com.adobe.cq.commerce.core.components.internal.models.v1.AbstractAssetsProvider;
import com.adobe.cq.commerce.core.components.models.common.ProductListItem;

public class RelatedProductsAssetsProvider extends AbstractAssetsProvider {

    public boolean canHandle(@Nonnull Resource resource) {
        return resource.isResourceType(RelatedProductsImpl.RESOURCE_TYPE);
    }

    public void addAssetPaths(@Nonnull Resource resource, @Nonnull Set<String> assetPaths) {
        RelatedProductsImpl relatedProducts = canHandle(resource) ? resource.adaptTo(RelatedProductsImpl.class) : null;
        if (relatedProducts != null) {
            for (ProductListItem item : relatedProducts.getProducts()) {
                String imageUrl = item.getImageURL();
                if (isAemAsset(imageUrl)) {
                    assetPaths.add(imageUrl);
                }
            }
        }
    }

}