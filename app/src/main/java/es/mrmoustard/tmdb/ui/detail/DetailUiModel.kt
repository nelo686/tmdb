package es.mrmoustard.tmdb.ui.detail

import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus
import es.mrmoustard.tmdb.domain.entities.MovieDetail

sealed class DetailUiModel {
    object Loading : DetailUiModel()
    object ErrorResponse : DetailUiModel()
    class Content(val movie: MovieDetail) : DetailUiModel()
    class Flags(val flags: MovieStatus) : DetailUiModel()
}
