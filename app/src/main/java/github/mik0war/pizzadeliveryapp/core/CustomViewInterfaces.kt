package github.mik0war.entity

interface CustomViewInterface<T>{
    fun set(content: T)
}
interface CustomTextViewInterface : CustomViewInterface<String>

