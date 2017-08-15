(ns tic-tac-toe.core
  (:require [reagent.core :as reagent :refer [atom]]
            [tic-tac-toe.logic :as logic]))

(defonce app-state
  (atom {:board (logic/new-board logic/board-size)}))

(defn naught [i j]
  [:circle {:id "naught"
            :r 0.45
            :cx (+ 0.5 i)
            :cy (+ 0.5 j)}])

(defn cross [i j]
  [:g {:stroke "black"
       :stroke-width 0.08
       :stroke-linecap "round"}
   [:line {:x1 i :y1 j :x2 (+ i 1) :y2 (+ j 1)}]
   [:line {:x1 i :y1 (+ j 1) :x2 (+ i 1) :y2 j}]])

(defn blank [i j]
  [:rect {:id "blank"
          :width 0.9
          :height 0.9
          :x (+ 0.05 i)
          :y (+ 0.05 j)}])

(defn tictactoe []
  [:div
   [:h1 "Welcome to Tic Tac Toe"]
   (into
    [:svg {:view-box "0 0 3 3"
           :width 500 :height 500}]
    (for [i (range logic/board-size)
          j (range logic/board-size)]
      (case (get-in @app-state [:board j i])
        0 [cross i j]
        1 [cross i j]
        2 [naught i j])))])

(enable-console-print!)
(reagent/render-component [tictactoe]
                          (. js/document (getElementById "app")))
