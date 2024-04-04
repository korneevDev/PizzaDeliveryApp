package github.mik0war.pizzadeliveryapp.core

import java.io.IOException

class NoConnectionException : IOException()
class ServiceUnavailableException : IOException()

class NoCachedDataException : IOException() {
}