package issue

/**
 * An entity with single-character field names gives compile time exceptions.
 *
 * .../grails-app/domain/issue/PersonLink.groovy: -1: The method public java.lang.Object getAId()  { ... } duplicates another method of the same signature
 * . At [-1:-1]  @ line -1, column -1.
 * .../grails-app/domain/issue/PersonLink.groovy: -1: Repetitive method name/signature for method 'java.lang.Object getAId()' in class 'issue.PersonLink'.
 * @ line -1, column -1.
 * .../grails-app/domain/issue/PersonLink.groovy: -1: Repetitive method name/signature for method 'java.lang.Object getBId()' in class 'issue.PersonLink'.
 * @ line -1, column -1.
 * .../grails-app/domain/issue/PersonLink.groovy: -1: Repetitive method name/signature for method 'java.lang.Object getAId()' in class 'issue.PersonLink'.
 * @ line -1, column -1.
 * .../grails-app/domain/issue/PersonLink.groovy: -1: Repetitive method name/signature for method 'java.lang.Object getBId()' in class 'issue.PersonLink'.
 */
class PersonLink {
    Person a
    Person b

    String toString() {
        "$a -> $b"
    }
}
