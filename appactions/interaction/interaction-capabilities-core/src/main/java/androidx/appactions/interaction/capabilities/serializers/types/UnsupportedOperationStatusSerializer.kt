/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.appactions.interaction.capabilities.serializers.types

import androidx.appactions.builtintypes.types.UnsupportedOperationStatus
import androidx.appactions.interaction.capabilities.core.impl.converters.TypeSpec
import androidx.appactions.interaction.capabilities.core.impl.converters.TypeSpecBuilder
import androidx.appactions.interaction.capabilities.serializers.properties.NAME_TYPE_SPEC
import androidx.appactions.interaction.capabilities.serializers.properties.TEXT_ONLY_DISAMBIGUATING_DESCRIPTION_TYPE_SPEC

val UNSUPPORTED_OPERATION_STATUS_TYPE_SPEC: TypeSpec<UnsupportedOperationStatus> =
TypeSpecBuilder.newBuilder(
  "UnsupportedOperationStatus",
  UnsupportedOperationStatus::Builder,
  UnsupportedOperationStatus.Builder<*>::build
).bindSpecField(
  "disambiguatingDescription",
  { it.disambiguatingDescription },
  UnsupportedOperationStatus.Builder<*>::setDisambiguatingDescription,
  TEXT_ONLY_DISAMBIGUATING_DESCRIPTION_TYPE_SPEC
).bindSpecField(
  "name",
  { it.name },
  UnsupportedOperationStatus.Builder<*>::setName,
  NAME_TYPE_SPEC
).bindStringField(
  "identifier",
  { it.identifier },
  UnsupportedOperationStatus.Builder<*>::setIdentifier
).bindIdentifier {
  it.identifier
}.build()
