package es.mrmoustard.tmdb.ui.detail

import es.mrmoustard.tmdb.domain.entities.MovieDetail
import es.mrmoustard.tmdb.domain.entities.MovieFlags

sealed class DetailUiModel {
    object Loading : DetailUiModel()
    object ErrorResponse : DetailUiModel()
    class Content(val movie: MovieDetail) : DetailUiModel()
    class Flags(val flags: MovieFlags) : DetailUiModel()
}
