
(def version "1.0.0")

(set-env!
  :resource-paths #{"src"}
  :source-paths #{"test"}
  :dependencies '[
  [org.clojure/clojure "1.8.0"]
  [edu.stanford.nlp/stanford-corenlp "3.5.2"]
  [edu.stanford.nlp/stanford-corenlp "3.5.2" :classifier "models"]
  [edu.cmu.cs/ark-tweet-nlp "0.3.2"]
  [seancorfield/boot-expectations "1.0.11" :scope "test"]])

(require '[seancorfield.boot-expectations :refer :all])

(deftask build []
(comp (pom) (jar) (install)))


(deftask dev
  "Profile setup for development.
  	Starting the repl with the dev profile...
  	boot dev repl "
  []
  (println "Dev profile running")
  (set-env!
   :init-ns 'user
   :source-paths #(into % ["src" "test"])
   :dependencies #(into % '[[org.clojure/tools.namespace "0.2.11"]]))

  ;; Makes clojure.tools.namespace.repl work per https://github.com/boot-clj/boot/wiki/Repl-reloading
  (require 'clojure.tools.namespace.repl)
  (eval '(apply clojure.tools.namespace.repl/set-refresh-dirs
                (get-env :directories)))

  identity)
