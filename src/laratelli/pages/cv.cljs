(ns laratelli.pages.cv
  "Inlined CV. I don't particularly like inlining it over parsing it from markdown,
  but I didn't want to think through specifying custom classes in the markdown
  and then styling the badges from there. As a bonus, though, inlining this is
  faster than parsing and lowering markdown on page load."
  (:require
   ["@dracula/dracula-ui" :as drac]
   [laratelli.lowering :refer [parse]])
  (:require-macros [laratelli.parsed-posts :refer [get-resource]]))

(defn skills-list [title skills]
  [:div
   [:> drac/Heading {:size "L"} title]
   [:> drac/List
    (map #(vector :li {:className "drac-text drac-text-white" :key %} %) skills)]])

(defn employment-badges [[role time-span location]]
  (let [style {:margin-left "0em"
               :padding-left 6
               :padding-right 6
               :padding-top 2
               :padding-bottom 2}]

    [:> drac/List {:style {:padding-left 0
                           :margin -1}}

     [:li {:style {:margin-top ".5em"
                   :margin-bottom ".5em"}}
      [:> drac/Badge {:variant "outline" :color "green"
                      :m "xs" :style style}
       role]]
     [:li
      {:style {:margin-top ".5em"
               :margin-bottom ".5em"}}
      [:> drac/Badge {:variant "subtle" :color "orange"
                      :m "xs" :style style}
       time-span]]
     [:li
      {:style {:margin-top ".5em"
               :margin-bottom ".5em"}}
      [:> drac/Badge {:variant "normal" :color "purpleCyan"
                      :m "xs" :style style}
       location]]]))

(def education-page
  [:div
   [:> drac/Heading {:size "xl"}
    "Education"]
   [:div
    [:> drac/Heading {:size "l"}
     "Master of Science in Computer Science"]
    (employment-badges ["University of South Florida"
                        "May 2021"
                        "Tampa, FL"])]
   [:div
    [:> drac/Heading {:size "l"}
     "Bachelor of Science in Chemistry"]
    (employment-badges ["University of South Florida"
                        "May 2018"
                        "Tampa, FL"])]
   [:div
    [:> drac/Heading {:size "l"}
     "High School"]
    (employment-badges ["Belen Jesuit Preparatory School"
                        "May 2013"
                        "Miami, FL"])]
   [:div
    [:> drac/Heading {:size "l"}
     "Other"]
    [:> drac/Text "I completed eighteen credit hours in the Ph.D. Chemistry
    program at USF between August 2018 and May 2019, focusing on research in
    computational chemistry. I decided to transfer into the Computer Science
    program sometime during the spring of 2019, and began taking classes for
    that degree that summer."]]])

(defn other-work [employer [role time-span location] description]
  [:div
   {:style {:padding-bottom "1em"}}
   [:> drac/Heading {:size "l"}
    employer]
   (employment-badges [role
                       time-span
                       location])
   [:> drac/Text
    description]])

(def other-work-page
  [:div
   [:> drac/Heading {:size "xl"}
    "Other Work Experience"]
   (other-work "Apple (retail)"
               ["Technical Specialist => Technical Expert"
                "July 2019 -> November 2020"
                "Tampa, FL"]
               "I worked at the Genius Bar, doing assessment and resolution of
    software issues that occurred with Apple's mobile offerings: iPhone, iPad,
    and Apple Watch. In February of 2020 I was promoted to Technical Expert,
    which added physical repairs on iPhones to my responsibilities. I was
    consistently in the top five employees for troubleshooting sessions
    completed per hour and lowest average session duration. I also assisted the
    most customers out of anyone on the team in the fourth quarter of 2019.")
   (other-work  "University of South Florida Department of Chemistry"
                ["Graduate Teaching Assistant"
                 "August 2018 -> May 2019"
                 "Tampa, FL"]
                "I worked as a Lab TA for the General Chemistry I lab, where I
    was responsible for teaching students semester basic and intermediate
    experimental chemistry techniques, as well as the chemical theory behind the
    experiments performed. Taught students the basics of data analysis and
    reporting.Responsible for teaching students proper laboratory safety
    techniques and ensuring their safety at all times in the lab. This position
    required the communication of ideas using distinct methods, handling
    multiple tasks at once, and managing groups of people to reach a common
    goal.")
   (other-work
    "University of South Florida Space Lab"
    ["Undergraduate Researcher => Graduate Researcher"
     "September 2016 -> May 2019"
     "Tampa, FL"]
    [:div
     "I worked in Dr. Brian Space's lab, where I was responsible for conducting
     independent computational chemistry research, mentoring undergraduates, and
     contributing to the lab’s code base, a program called " [:code "mpmc"] ".
     Designed and implemented an " [:> drac/Anchor {:hoverColor "yellowPink"
                                                    :href "https://github.com/mpmccode/mpmc_testing"
                                                    :weight "bold"}
                                    "end-to-end testing program"] " for the
      lab’s code base that supports serial and parallelized testing. I improved
      the lab's code base by refactoring important sections, adjusting the build
      system to achieve cross-platform compilation, increasing output
      functionality, and constructing and adding a new set of simulation
      parameters. My research focused on the fugacity of various gases."])
   (other-work
    "University of South Florida Leahy Lab"
    ["Undergraduate Researcher"
     "August 2015 -> August 2016"
     "Tampa, FL"]
    [:div
     "I worked as an undergraduate researcher in Dr. James Leahy's lab, under
     the direction of Zach Shultz. This lab's focus was synthetic organic
     chemistry focused on developing reactions for natural products, chemicals
     found in nature with evidence of having desirable pharmaceutical
     properties. I honed my basic chemistry techniques (titrations,
     spectroscopic analysis, and purification) and got experience with more
     advanced reactions, challenging separations chemistry, and natural products
     extraction."])
   (other-work
    "University of Miami Miller School of Medicine Podack Lab"
    ["Undergraduate Research"
     "May 2014 -> August 2014"
     "Miami, FL"]
    "I worked as a (very green) undergraduate in the lab of Dr. Eckhard Podack.
    I started with doing basic cell culture, mostly on agar plates, and
    eventually transitioned to more advanced cultures and working with animal
    models (particularly mice, but also some rats), where I studied the effects
    of a novel compound against specific types of lung cancers.")])

(defn cv []
  [:div
   [:> drac/Heading {:size "xl"}
    "Professional Work Experience"]
   [:> drac/Heading {:size "L"}
    "Flexibits, Inc."]
   [:> drac/List {:style {:padding-left 0
                          :margin -1}}

    [:li {:style {:margin-top ".5em"
                  :margin-bottom ".5em"}}
     [:> drac/Badge {:variant "outline" :color "animated"
                     :m "xs" :style {:margin-left "0em"
                                     :padding-left 6
                                     :padding-right 6
                                     :padding-top 2
                                     :padding-bottom 2}}
      "Software Engineer"]]
    [:li
     {:style {:margin-top ".5em"
              :margin-bottom ".5em"}}
     [:> drac/Badge {:variant "subtle" :color "orange"
                     :m "xs" :style {:margin-left "0em"
                                     :padding-left 6
                                     :padding-right 6
                                     :padding-top 2
                                     :padding-bottom 2}}
      "March 2021 -> Present"]]
    [:li
     {:style {:margin-top ".5em"
              :margin-bottom ".5em"}}
     [:> drac/Badge {:variant "normal" :color "purpleCyan"
                     :m "xs" :style {:margin-left "0em"
                                     :padding-left 6
                                     :padding-right 6
                                     :padding-top 2
                                     :padding-bottom 2}}
      "Remote"]]]
   [:> drac/Text
    " I work on Fantastical and Cardhop for both mac and iOS. I'm the main
    developer working on Fantastical for the Apple Watch, and maintain our
    Shortcuts since migrating them to macOS. Lots of Objective-C with a little
    bit of Swift and SwiftUI sprinkled in."]
   [:> drac/Divider {:color "orange"}]
   [:> drac/Heading {:size "XL"} "Skills"]
   (skills-list "Computer Languages" (vector "Bash" "C" "C++" "Clojure(Script)" "Objective-C" "Python" "SQL" "Swift"))
   (skills-list "Computer Platforms and Frameworks" (vector "CUDA" "MPI" "SwiftUI"))
   (skills-list "Software Engineering Tools" (vector "CMake" "docker (and compose)" "emacs" "gdb" "git" "vim" "Xcode"))
   (skills-list "Software Engineering Tools" (vector "English (Native)" "Spanish (Native)"))
   [:> drac/Heading {:size "L"} "Operating Systems"]
   [:> drac/Text
    "I used to be pretty handy with Arch and other Linux distributions. These
    days I spend most of my time in macOS unless I'm working with my servers,
    which all run Ubuntu. I've (of course) used Windows, but only at the level
    of a gamer that tinkers with their machine."]
   [:> drac/Divider {:color "orange"}]
   ; TODO: inline me
   (parse (get-resource "cv/projects.md"))
   [:> drac/Divider {:color "orange"}]
   education-page
   [:> drac/Divider {:color "orange"}]
   other-work-page
   [:> drac/Divider {:color "orange"}]
   ; TODO: inline me
   (parse (get-resource "cv/awards.md"))])
