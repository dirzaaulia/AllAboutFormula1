package com.dirzaaulia.smartphonespec.utilities

import java.util.Locale

fun String.capitalizeWord() = replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }