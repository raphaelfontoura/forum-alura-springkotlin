package br.com.rddev.forum.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}
