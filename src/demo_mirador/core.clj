(ns demo-mirador.core
    (:require
      [org.httpkit.server :refer :all]
      [compojure.core :refer (GET POST ANY PUT DELETE defroutes)]
      [compojure.handler :as handler]
      [ring.middleware.session]
      [ring.middleware.reload]
      [net.cgrand.enlive-html :refer :all]
      [compojure.route :as r]
      [com.akolov.enlive-reload :refer [wrap-enlive-reload]]
      [com.akolov.mirador.core :refer [watch-reload watcher-folder]]
      )
    )


(defn html-response [body]
      {:status  200
       :headers {"Content-Type" "text/html; charset utf-8"}
       :body    body
      })

(defsnippet cookies-warning "pages/snippets.html" [:div#cookies-warning] [])
(defsnippet login-buttons "pages/snippets.html" [:div#login-buttons] [])

(deftemplate main-template "pages/template.html" [req main]
             [:div#cookies-warning]
             (substitute (cookies-warning))
             [:div#login-area] (login-buttons)
             [:div#page-content] main
             )

(defsnippet index-content "pages/index.html"
            [:div#main] []
            )


(defn welcome [req]
      (main-template req (substitute (index-content))))

(defn index [req]
      (->> (welcome req) (html-response req)))


(defroutes routes
           (GET "/" req (index req))
           (r/resources "/static" {:root "resources/static"})
           )

(def app (-> routes
             (ring.middleware.reload/wrap-reload)
             (wrap-enlive-reload)
             (watch-reload {:watcher (watcher-folder "resources")
                            :uri     "/watch-reload"})
             handler/site))

(defonce server (atom nil))

(defn boot-server []
      (reset! server (run-server #'app {:port 3001 :join? false})))

(defn start [] (.start @server))
(defn stop [] (.stop @server))
