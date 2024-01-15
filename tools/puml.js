import fs from 'node:fs';


/**
 * Represents graph in a plantUML format
 *
 * @param n {number} Number of Nodes
 * @param edges {[number, number][]} Edges [from, to[, 'bold'|'dotted'|...]][]
 * @param outputFile {string} Path to target .puml file
 */
export function puml(n, edges, outputFile = undefined) {
  const lines = ['@startuml'];

  for (let nodeId=0; nodeId < n; nodeId++) {
    lines.push(`circle "${nodeId}" as node${nodeId}`);
  }

  for (const [a, b, style] of edges) {
    if (style) {
      lines.push(`node${a}-[${style}]-node${b}`);
    } else {
      lines.push(`node${a}--node${b}`);
    }
  }

  lines.push('@enduml');

  const out = lines.join('\n');

  if (outputFile) {
    fs.writeFile(outputFile, out, (err) => {
      console.log(err);
    });
  }

  return out;
}
