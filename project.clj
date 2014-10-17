(defproject
  demo-mirador "0.1.0-SNAPSHOT"
  :description "Demo ring application to test mirador's working"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring/ring "1.2.1"]
                 [compojure "1.2.0-SNAPSHOT"]
                 [enlive "1.1.5"]
                 [com.akolov.enlive-reload "0.1.0"]
                 [com.akolov.mirador "0.1.0-SNAPSHOT"]
                 [http-kit "2.1.16"]
                 ]


  :repositories [["java.net" "http://download.java.net/maven/2"]]

  :min-lein-version "2.0.0"

  :resource-paths ["resources"]
  )

