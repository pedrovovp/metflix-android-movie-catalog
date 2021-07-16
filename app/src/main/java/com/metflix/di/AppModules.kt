package com.metflix.di

import com.metflix.data.di.dataModules
import com.metflix.data_local.di.localDataModules
import com.metflix.data_remote.di.networkModules
import com.metflix.domain.di.domainModules
import com.metflix.presentation.di.presentationModules


val appModules = presentationModules + networkModules + dataModules + domainModules + localDataModules + networkModules