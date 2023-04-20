import React, { useState, useRef, useEffect } from "react";
//import 'bootswatch/dist/lux/bootstrap.min.css';

interface Tab {
  id: number;
  value1: string;
  value2: string;
}

export const IntroSection: React.FC = () => {
  const [tabs, setTabs] = useState<Tab[]>([]);
  const [activeTab, setActiveTab] = useState<number | null>(null);

  //--------------------------------------------
  const [numLines, setNumLines] = useState<string[]>(["1"]);
  //--------------------------------------------

  const handleTabClick = (id: number) => {
    setActiveTab(id);
  };

  const handleAddTab = () => {
    const newId = tabs.length === 0 ? 1 : tabs[tabs.length - 1].id + 1;
    const newTab: Tab = { id: newId, value1: "", value2: "" };
    setTabs([...tabs, newTab]);
    setActiveTab(newId);
  };

  const handleDeleteTab = (id: number) => {
    const newTabs = tabs.filter((tab) => tab.id !== id);
    setTabs(newTabs);
    setActiveTab(newTabs.length === 0 ? null : newTabs[0].id);
  };

  const handleCopyValue = (id: number) => {
    const newTabs = [...tabs];
    const tabIndex = newTabs.findIndex((tab) => tab.id === id);
    const value1 = newTabs[tabIndex].value1;
    newTabs[tabIndex] = { ...newTabs[tabIndex], value2: value1 };
    setTabs(newTabs);
  };

  //-------------------------------------
  function handleTextareaKeyUp(
    event: React.KeyboardEvent<HTMLTextAreaElement>
  ): void {
    const numberOfLines = event.currentTarget.value.split("\n").length;
    setNumLines((prevNumLines) => {
      const newNumLines = Array(numberOfLines)
        .fill(null)
        .map((_, index) => String(index + 1));
      return newNumLines;
    });
  }

  function handleTextareaKeyDown(
    event: React.KeyboardEvent<HTMLTextAreaElement>
  ): void {
    if (event.key === "Tab") {
      const { selectionStart, selectionEnd, value } = event.currentTarget;
      event.currentTarget.value =
        value.substring(0, selectionStart) +
        "\t" +
        value.substring(selectionEnd);
      event.currentTarget.selectionStart = event.currentTarget.selectionEnd =
        selectionStart + 1;
      event.preventDefault();
    }
  }
  //-------------------------------

  return (
    <>
      <h2 className="title-compiler">Analizador</h2>
      <div className="tabs-section">
        <div className="tabs-container">
          {tabs.map((tab) => (
            <div
              key={tab.id}
              className={`tab ${activeTab === tab.id ? "active" : ""}`}
              onClick={() => handleTabClick(tab.id)}
            >
              P{tab.id}
              <button
                className="delete-tab"
                onClick={() => handleDeleteTab(tab.id)}
              >
                𝒙
              </button>
            </div>
          ))}
          <button className="add-tab" onClick={handleAddTab}>
            +
          </button>
        </div>
        <div className="tab-content">
          {activeTab !== null && (
            <>
              <div className="content-text">
                <div className="editor">
                  <div className="line-numbers">
                    {numLines.map((num, index) => (
                      <span key={index}>
                        {num}
                        <br />
                      </span>
                    ))}
                  </div>
                  <textarea
                    className="text-area"
                    value={tabs.find((tab) => tab.id === activeTab)?.value1}
                    onChange={(e) => {
                      const newTabs = [...tabs];
                      const tabIndex = newTabs.findIndex(
                        (tab) => tab.id === activeTab
                      );
                      newTabs[tabIndex] = {
                        ...newTabs[tabIndex],
                        value1: e.target.value,
                      };
                      setTabs(newTabs);
                    }}
                    onKeyUp={handleTextareaKeyUp}
                    onKeyDown={handleTextareaKeyDown}
                  ></textarea>
                </div>
                <div className="editor">
                  <div className="line-numbers">
                  {numLines.map((num, index) => (
                      <span key={index}>
                        {num}
                        <br />
                      </span>
                    ))}
                  </div>
                  <textarea
                    className="text-area"
                    value={tabs.find((tab) => tab.id === activeTab)?.value2}
                    readOnly
                    onKeyUp={handleTextareaKeyUp}
                    onKeyDown={handleTextareaKeyDown}
                  ></textarea>
                </div>
              </div>
              <div className="content-button">
                <button className="open-file">Open File</button>
                <button
                  className="acept-analyze"
                  onClick={() => handleCopyValue(activeTab)}
                >
                  Analyze
                </button>
                <button className="save-file">Save File</button>
              </div>
            </>
          )}
        </div>
      </div>
    </>
  );
};
