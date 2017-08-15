(ns tic-tac-toe.core
  (:require [reagent.core :as reagent :refer [atom]]
            [tic-tac-toe.logic :as logic]))

(defonce app-state
  (atom {:board (logic/new-board logic/board-size)}))

(defn naught [i j]
  [:g
   [:rect {:id "back"
           :x (+ 0.05 i)
           :y (+ 0.05 j)}]
   [:circle {:id "naught"
             :cx (+ 0.5 i)
             :cy (+ 0.5 j)}]])

(defn cross [i j]
  [:g 
   [:rect {:id "back"
           :x (+ 0.05 i)
           :y (+ 0.05 j)}]
   [:g {:id "cross"}
    (let [offset 0.25]
      [:line {:x1 (+ i offset) :y1 (+ j offset) :x2 (+ i (- 1 offset)) :y2 (+ j (- 1 offset))}])
    (let [offset 0.25]
      [:line {:x1 (+ i offset) :y1 (+ j (- 1 offset)) :x2 (+ i (- 1 offset)) :y2 (+ j offset)}])]])

(defn blank [i j]
  [:rect {:id "blank"
          :x (+ 0.05 i)
          :y (+ 0.05 j)
          :on-click #(swap! app-state assoc-in [:board j i] 1)}])

(defn tictactoe []
  [:div
   [:h1 "Welcome to Tic Tac Toe"]
   (into
    [:svg {:view-box (str "0 0 " logic/board-size " " logic/board-size)
           :width 500 :height 500}]
    (for [i (range logic/board-size)
          j (range logic/board-size)]
      (case (get-in @app-state [:board j i])
        0 [blank i j]
        1 [cross i j]
        2 [naught i j])))])

(enable-console-print!)
(reagent/render-component [tictactoe]
                          (. js/document (getElementById "app")))
